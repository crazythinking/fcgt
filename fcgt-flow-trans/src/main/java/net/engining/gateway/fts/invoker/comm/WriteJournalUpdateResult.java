package net.engining.gateway.fts.invoker.comm;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import net.engining.control.api.key.TransIdKey;
import net.engining.control.core.flow.FlowContext;
import net.engining.control.core.invoker.Invoker;
import net.engining.control.core.invoker.InvokerDefinition;
import net.engining.control.core.invoker.Skippable;
import net.engining.gateway.entity.enums.TransStatusDef;
import net.engining.gateway.entity.model.CtErrorJournal;
import net.engining.gateway.entity.model.CtInboundJournal;
import net.engining.gateway.fts.sdk.bean.resultBack.ResponseData;
import net.engining.gateway.fts.sdk.key.ResponseDataKey;
import net.engining.pg.web.WebCommonResponse;

@InvokerDefinition(
		name = "更新联机日志处理结果",
		requires = {
		},
		optional = {
				
		}, 
		results = {
				
		}
		)
public class WriteJournalUpdateResult implements Invoker, Skippable {

	private static final Logger log = LoggerFactory.getLogger(WriteJournalUpdateResult.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public void invoke(FlowContext ctx) {
		
		CtErrorJournal ctErrorJournal = new CtErrorJournal();
		String transId= ctx.get(TransIdKey.class);
		CtInboundJournal ctInboundJournal = em.find(CtInboundJournal.class, transId);
		ctInboundJournal.setResponseMsg(JSON.toJSONString(ctx.get(ResponseDataKey.class)));
		ResponseData responseData = new ResponseData();
		responseData.setReturnCode(WebCommonResponse.CODE_OK);
		responseData.setReturnDesc(WebCommonResponse.DESC_SUCCESS);
		if (JSON.toJSONString(responseData).equals(ctInboundJournal.getResponseMsg())) {
			ctInboundJournal.setTransStatus(TransStatusDef.S);
		} else if (JSON.toJSONString(responseData) == null){
			ctInboundJournal.setTransStatus(TransStatusDef.F);
			ctErrorJournal.setInboundId(ctInboundJournal.getInboundId());
			ctErrorJournal.setCreateTime(ctInboundJournal.getCreateTime());
			ctErrorJournal.setUpdateTime(ctInboundJournal.getUpdateTime());
			ctErrorJournal.setJpaVersion(ctInboundJournal.getJpaVersion());
			em.persist(ctErrorJournal);
		} else {
			ctInboundJournal.setTransStatus(TransStatusDef.F);
			ctErrorJournal.setInboundId(ctInboundJournal.getInboundId());
			ctErrorJournal.setErrorCode("9999");
			ctErrorJournal.setErrorReason(ctx.get(ResponseDataKey.class).getReturnDesc());
			ctErrorJournal.setCreateTime(ctInboundJournal.getCreateTime());
			ctErrorJournal.setUpdateTime(ctInboundJournal.getUpdateTime());
			ctErrorJournal.setJpaVersion(ctInboundJournal.getJpaVersion());
			em.persist(ctErrorJournal);
		}
	}

	@Override
	public boolean skippable(Map<String, String> parameters) {
		String skip = parameters.get(FlowContext.CONS_PARAMETERS.SKIP.toString());
		if("Yes".equals(skip)){
			return true;
		}
		return false;
	}

}
