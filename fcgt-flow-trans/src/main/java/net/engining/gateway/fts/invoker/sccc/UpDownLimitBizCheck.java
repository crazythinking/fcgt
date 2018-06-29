package net.engining.gateway.fts.invoker.sccc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import net.engining.control.api.key.ChannelKey;
import net.engining.control.api.key.ChannelRequestSeqKey;
import net.engining.control.api.key.ChannelSignTokenKey;
import net.engining.control.api.key.SvPrIdKey;
import net.engining.control.api.key.TxnDateTimeKey;
import net.engining.control.core.flow.FlowContext;
import net.engining.control.core.invoker.Invoker;
import net.engining.control.core.invoker.InvokerDefinition;
import net.engining.control.core.invoker.Skippable;
import net.engining.gateway.fts.sao.sccc.AccountingSao;
import net.engining.gateway.fts.sdk.bean.BizData;
import net.engining.gateway.fts.sdk.bean.EveryDayAccountingBean;
import net.engining.gateway.fts.sdk.bean.RequestData;
import net.engining.gateway.fts.sdk.bean.UnionData;
import net.engining.gateway.fts.sdk.bean.limit.AccountLimitRequestData;
import net.engining.gateway.fts.sdk.enums.AccountTradingDef;
import net.engining.gateway.fts.sdk.enums.CheckDataResDef;
import net.engining.gateway.fts.sdk.key.AsynIndKey;
import net.engining.gateway.fts.sdk.key.DataCheckResultKey;
import net.engining.gateway.fts.sdk.key.EveryDayAccountingBeanKey;
import net.engining.gateway.fts.sdk.key.OnlineDataKey;
import net.engining.pg.support.core.exception.ErrorCode;

/**
 * 
 * 
 * @author luxue
 *
 */
@InvokerDefinition(
		name = "授额|提降额记账交易数据及业务检查", 
		requires = { 
				OnlineDataKey.class,
		        ChannelRequestSeqKey.class 
		}, 
		results = { 
				DataCheckResultKey.class 
				}
		)
public class UpDownLimitBizCheck implements Invoker, Skippable {

	private static final Logger log = LoggerFactory.getLogger(UpDownLimitBizCheck.class);

	@Autowired
	private AccountingSao outerLedgerTxnHstSao;

	@Override
	public void invoke(FlowContext ctx) {
		// SAO
		JSONObject jsonObject = JSONObject.parseObject(ctx.get(OnlineDataKey.class));
		AccountLimitRequestData data = (AccountLimitRequestData) JSONObject.toJavaObject(jsonObject,
				AccountLimitRequestData.class);
		//联合贷额度数据
		List<UnionData> unionDataList = new ArrayList<UnionData>();
		if (data.getUnionData().size() > 0) {
			for (UnionData unionData : data.getUnionData()) {
				UnionData union = new UnionData();
				union.setOtherAmt(unionData.getOtherAmt());
				union.setOwnAmt(unionData.getOwnAmt());
				unionDataList.add(union);
			}
		}

		//辅助核算项数据
		List<BizData> bizDataList = new ArrayList<BizData>();
		if (data.getBizData().size() > 0) {
			for (BizData bizData : data.getBizData()) {
				BizData biz = new BizData();
				biz.setKeyId(bizData.getKeyId());
				biz.setFieldDesc(bizData.getFieldDesc());
				biz.setIsAssisting(bizData.getIsAssisting());
				biz.setValue(bizData.getValue());
				bizDataList.add(biz);
			}
		}

		//报文体
		RequestData requestData = new RequestData();
		requestData.setProductId(data.getProductId());
		requestData.setCustId(data.getCustId());
		requestData.setTotalAmt(data.getTotalAmt());
		requestData.setAccountTrading(AccountTradingDef.S);
		requestData.setCreditLimitAmt(data.getCreditLimitAmt());
		requestData.setCurrency(data.getCurrency());
		requestData.setSysInternalAcctActionCd(data.getSysInternalAcctActionCd());
		requestData.setIsUnion(data.getIsUnion());
		requestData.setBizData(bizDataList);
		requestData.setUnionData(unionDataList);
		
		//报文
		EveryDayAccountingBean bean = new EveryDayAccountingBean();
		bean.setSvPrId(ctx.get(SvPrIdKey.class));
		bean.setChannelId(ctx.get(ChannelKey.class));
		bean.setChannelSign(ctx.get(ChannelSignTokenKey.class));
		bean.setClearDate(ctx.get(TxnDateTimeKey.class));
		bean.setTxnSerialNo(ctx.get(ChannelRequestSeqKey.class));
		bean.setTimestamp(ctx.get(TxnDateTimeKey.class));
		bean.setAsynInd(ctx.get(AsynIndKey.class).toString());
		bean.setRequestData(requestData);
		//数据校验
		String result = outerLedgerTxnHstSao.accountLimit(bean);
		if (!result.equals(CheckDataResDef.SUCCESS.toString())) {
			log.warn("数据记录出现异常：{}", ErrorCode.BadRequest + "; " + result + ctx.get(ChannelRequestSeqKey.class));
			ctx.getParameters().put(FlowContext.CONS_PARAMETERS.SKIP.toString(), "true");
			
		}

		ctx.put(EveryDayAccountingBeanKey.class, bean);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.engining.control.core.invoker.Skippable#skippable(java.util.Map)
	 */
	@Override
	public boolean skippable(Map<String, String> parameters) {
		String skip = parameters.get(FlowContext.CONS_PARAMETERS.SKIP.toString());
		if("Yes".equals(skip)){
			return true;
		}
		return false;
	}

}
