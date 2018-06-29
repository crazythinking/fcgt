package net.engining.gateway.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.engining.gateway.fts.sdk.bean.inteAcc.CorrectionInteAccRequest;
import net.engining.gateway.fts.sdk.bean.inteAcc.CorrectionInteAccRequestData;
import net.engining.gateway.fts.sdk.bean.correction.CorrectionRepaymentRequest;
import net.engining.gateway.fts.sdk.bean.correction.CorrectionRepaymentRequestData;
import net.engining.gateway.fts.sdk.bean.cost.CostAccountRequest;
import net.engining.gateway.fts.sdk.bean.cost.CostAccountRequestData;
import net.engining.gateway.fts.sdk.bean.init.CorrectionInteRequest;
import net.engining.gateway.fts.sdk.bean.init.CorrectionInteRequestData;
import net.engining.gateway.fts.sdk.bean.limit.AccountLimitRequestData;
import net.engining.gateway.fts.sdk.bean.limit.LimitRequest;
import net.engining.gateway.fts.sdk.bean.loan.LoanAccountRequest;
import net.engining.gateway.fts.sdk.bean.loan.LoanAccountRequestData;
import net.engining.gateway.fts.sdk.bean.pnit.CorrectionPintRequest;
import net.engining.gateway.fts.sdk.bean.pnit.CorrectionPintRequestData;
import net.engining.gateway.fts.sdk.bean.pnitAcc.CorrectionPnitAccRequest;
import net.engining.gateway.fts.sdk.bean.pnitAcc.CorrectionPnitAccRequestData;
import net.engining.gateway.fts.sdk.bean.refunds.RefundsRequest;
import net.engining.gateway.fts.sdk.bean.refunds.RefundsRequestData;
import net.engining.gateway.fts.sdk.bean.repayment.RePaymentRequest;
import net.engining.gateway.fts.sdk.bean.repayment.RepaymentRequestData;
import net.engining.gateway.fts.sdk.bean.resultBack.ResponseData;
import net.engining.gateway.fts.sdk.bean.transfer.CorrectionTransfoRequest;
import net.engining.gateway.fts.sdk.bean.transfer.CorrectionTransfoRequestData;
import net.engining.gateway.service.ScccAccountMqProducer;
import net.engining.pg.support.core.exception.ErrorCode;
import net.engining.pg.support.core.exception.ErrorMessageException;
import net.engining.pg.web.AsynInd;
import net.engining.pg.web.WebCommonRequest;
import net.engining.pg.web.WebCommonResponse;
import net.engining.pg.web.WebCommonResponseBuilder;

/**
 * 授额|提降额记账 Online 接口
 * @author luxue
 *
 */
@RestController
@RequestMapping("/scccAccounting")
public class ScccAccountOnlineController {
	
	private static final Logger log = LoggerFactory.getLogger(ScccAccountOnlineController.class);
	
	@Autowired
	ScccAccountMqProducer scccAccountMqProducer;
	
	@ApiOperation(value = "授额|提降额记账", notes = "")
	@RequestMapping(value = "/upDownLimetAmount", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> upDownLimetAmount(@RequestBody WebCommonRequest<AccountLimitRequestData> requestBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		LimitRequest limitRequest = new LimitRequest();
		limitRequest.setRequestData(requestBean.getRequestData());
		limitRequest.setAsynInd(requestBean.getAsynInd());
		limitRequest.setChannelId(requestBean.getChannelId());
		limitRequest.setChannelSign(requestBean.getChannelSign());
		limitRequest.setClearingDate(requestBean.getClearingDate());
		limitRequest.setTimestamp(requestBean.getTimestamp());
		limitRequest.setTxnSerialNo(requestBean.getTxnSerialNo());
		limitRequest.setSvPrId(requestBean.getSvPrId());
		limitRequest.setAdditionalReqMap(requestBean.getAdditionalReqMap());

		if(limitRequest.getRequestData().getCreditLimitAmt().compareTo(BigDecimal.ZERO)==0){
			throw new ErrorMessageException(ErrorCode.BadRequest, "信用额度值不能为0");
		}
		
		if(AsynInd.A.equals(limitRequest.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4UpDownLimetAmount(limitRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}else if(AsynInd.S.equals(limitRequest.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}
	
	@ApiOperation(value = "费用收取记账", notes = "")
	@RequestMapping(value = "/costAccount", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> costAccount(@RequestBody WebCommonRequest<CostAccountRequestData> requestBean) {

		CostAccountRequest costAccountRequest = new CostAccountRequest();
		costAccountRequest.setAsynInd(requestBean.getAsynInd());
		costAccountRequest.setChannelId(requestBean.getChannelId());
		costAccountRequest.setChannelSign(requestBean.getChannelSign());
		costAccountRequest.setClearingDate(requestBean.getClearingDate());
		costAccountRequest.setRequestData(requestBean.getRequestData());
		costAccountRequest.setTimestamp(requestBean.getTimestamp());
		costAccountRequest.setTxnSerialNo(requestBean.getTxnSerialNo());
		costAccountRequest.setSvPrId(requestBean.getSvPrId());
		costAccountRequest.setAdditionalReqMap(requestBean.getAdditionalReqMap());

		if(AsynInd.A.equals(requestBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CostAccount(costAccountRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(requestBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}
	
	@ApiOperation(value = "贷款发放记账交易", notes = "")
	@RequestMapping(value = "/loanAccount", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> loanAccount(@RequestBody WebCommonRequest<LoanAccountRequestData>  requestBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		LoanAccountRequest loanAccountRequest = new LoanAccountRequest();
		loanAccountRequest.setAsynInd(requestBean.getAsynInd());
		loanAccountRequest.setChannelId(requestBean.getChannelId());
		loanAccountRequest.setChannelSign(requestBean.getChannelSign());
		loanAccountRequest.setClearingDate(requestBean.getClearingDate());
		loanAccountRequest.setRequestData(requestBean.getRequestData());
		loanAccountRequest.setTimestamp(requestBean.getTimestamp());
		loanAccountRequest.setTxnSerialNo(requestBean.getTxnSerialNo());
		loanAccountRequest.setSvPrId(requestBean.getSvPrId());
		loanAccountRequest.setAdditionalReqMap(requestBean.getAdditionalReqMap());

		if(AsynInd.A.equals(requestBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4LoanAccount(loanAccountRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(requestBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}
	
	@ApiOperation(value = "退款记账交易", notes = "")
	@RequestMapping(value = "/refundsAccount", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> refundsAccount(@RequestBody WebCommonRequest<RefundsRequestData>  requestBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		RefundsRequest refundsRequest = new RefundsRequest();
		refundsRequest.setAsynInd(requestBean.getAsynInd());
		refundsRequest.setChannelId(requestBean.getChannelId());
		refundsRequest.setChannelSign(requestBean.getChannelSign());
		refundsRequest.setClearingDate(requestBean.getClearingDate());
		refundsRequest.setRequestData(requestBean.getRequestData());
		refundsRequest.setTimestamp(requestBean.getTimestamp());
		refundsRequest.setTxnSerialNo(requestBean.getTxnSerialNo());
		refundsRequest.setSvPrId(requestBean.getSvPrId());
		refundsRequest.setAdditionalReqMap(requestBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(requestBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4RefundsAccount(refundsRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
			
		}
		else if(AsynInd.S.equals(requestBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}
	
	@ApiOperation(value = "还款记账交易", notes = "")
	@RequestMapping(value = "/repaymentAccount", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> repaymentAccount(@RequestBody WebCommonRequest<RepaymentRequestData>  requestBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		RePaymentRequest rePaymentRequest = new RePaymentRequest();
		rePaymentRequest.setAsynInd(requestBean.getAsynInd());
		rePaymentRequest.setChannelId(requestBean.getChannelId());
		rePaymentRequest.setChannelSign(requestBean.getChannelSign());
		rePaymentRequest.setClearingDate(requestBean.getClearingDate());
		rePaymentRequest.setRequestData(requestBean.getRequestData());
		rePaymentRequest.setTimestamp(requestBean.getTimestamp());
		rePaymentRequest.setTxnSerialNo(requestBean.getTxnSerialNo());
		rePaymentRequest.setSvPrId(requestBean.getSvPrId());
		rePaymentRequest.setAdditionalReqMap(requestBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(requestBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4RepaymentAccount(rePaymentRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(requestBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}

	@ApiOperation(value = "冲正还款记账交易", notes = "")
	@RequestMapping(value = "/correctionRepayment", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> correctionRepayment(@RequestBody WebCommonRequest<CorrectionRepaymentRequestData>  correctionBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		CorrectionRepaymentRequest correctionRequest = new CorrectionRepaymentRequest();
		correctionRequest.setAsynInd(correctionBean.getAsynInd());
		correctionRequest.setChannelId(correctionBean.getChannelId());
		correctionRequest.setChannelSign(correctionBean.getChannelSign());
		correctionRequest.setClearingDate(correctionBean.getClearingDate());
		correctionRequest.setRequestData(correctionBean.getRequestData());
		correctionRequest.setTimestamp(correctionBean.getTimestamp());
		correctionRequest.setTxnSerialNo(correctionBean.getTxnSerialNo());
		correctionRequest.setSvPrId(correctionBean.getSvPrId());
		correctionRequest.setAdditionalReqMap(correctionBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(correctionBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CorrectionRepayment(correctionRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(correctionBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}

	@ApiOperation(value = "冲正利息计提", notes = "")
	@RequestMapping(value = "/correctionInteAcc", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> correctionInteAcc(@RequestBody WebCommonRequest<CorrectionInteAccRequestData>  correctionBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		CorrectionInteAccRequest correctionRequest = new CorrectionInteAccRequest();
		correctionRequest.setAsynInd(correctionBean.getAsynInd());
		correctionRequest.setChannelId(correctionBean.getChannelId());
		correctionRequest.setChannelSign(correctionBean.getChannelSign());
		correctionRequest.setClearingDate(correctionBean.getClearingDate());
		correctionRequest.setRequestData(correctionBean.getRequestData());
		correctionRequest.setTimestamp(correctionBean.getTimestamp());
		correctionRequest.setTxnSerialNo(correctionBean.getTxnSerialNo());
		correctionRequest.setSvPrId(correctionBean.getSvPrId());
		correctionRequest.setAdditionalReqMap(correctionBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(correctionBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CorrectionInteAcc(correctionRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(correctionBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}

	@ApiOperation(value = "冲正罚息计提", notes = "")
	@RequestMapping(value = "/correctionPnitAcc", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> correctionPnitAcc(@RequestBody WebCommonRequest<CorrectionPnitAccRequestData>  correctionBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		CorrectionPnitAccRequest correctionRequest = new CorrectionPnitAccRequest();
		correctionRequest.setAsynInd(correctionBean.getAsynInd());
		correctionRequest.setChannelId(correctionBean.getChannelId());
		correctionRequest.setChannelSign(correctionBean.getChannelSign());
		correctionRequest.setClearingDate(correctionBean.getClearingDate());
		correctionRequest.setRequestData(correctionBean.getRequestData());
		correctionRequest.setTimestamp(correctionBean.getTimestamp());
		correctionRequest.setTxnSerialNo(correctionBean.getTxnSerialNo());
		correctionRequest.setSvPrId(correctionBean.getSvPrId());
		correctionRequest.setAdditionalReqMap(correctionBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(correctionBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CorrectionPnitAcc(correctionRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(correctionBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}

	@ApiOperation(value = "冲正本金结转", notes = "")
	@RequestMapping(value = "/correctionLbal", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> correctionLbal(@RequestBody WebCommonRequest<CorrectionTransfoRequestData>  correctionBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		CorrectionTransfoRequest correctionRequest = new CorrectionTransfoRequest();
		correctionRequest.setAsynInd(correctionBean.getAsynInd());
		correctionRequest.setChannelId(correctionBean.getChannelId());
		correctionRequest.setChannelSign(correctionBean.getChannelSign());
		correctionRequest.setClearingDate(correctionBean.getClearingDate());
		correctionRequest.setRequestData(correctionBean.getRequestData());
		correctionRequest.setTimestamp(correctionBean.getTimestamp());
		correctionRequest.setTxnSerialNo(correctionBean.getTxnSerialNo());
		correctionRequest.setSvPrId(correctionBean.getSvPrId());
		correctionRequest.setAdditionalReqMap(correctionBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(correctionBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CorrectionLbal(correctionRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(correctionBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}

	@ApiOperation(value = "冲正利息结转", notes = "")
	@RequestMapping(value = "/correctionInte", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> correctionInte(@RequestBody WebCommonRequest<CorrectionInteRequestData>  correctionBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		CorrectionInteRequest correctionRequest = new CorrectionInteRequest();
		correctionRequest.setAsynInd(correctionBean.getAsynInd());
		correctionRequest.setChannelId(correctionBean.getChannelId());
		correctionRequest.setChannelSign(correctionBean.getChannelSign());
		correctionRequest.setClearingDate(correctionBean.getClearingDate());
		correctionRequest.setRequestData(correctionBean.getRequestData());
		correctionRequest.setTimestamp(correctionBean.getTimestamp());
		correctionRequest.setTxnSerialNo(correctionBean.getTxnSerialNo());
		correctionRequest.setSvPrId(correctionBean.getSvPrId());
		correctionRequest.setAdditionalReqMap(correctionBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(correctionBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CorrectionInte(correctionRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(correctionBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}

	@ApiOperation(value = "冲正罚息结转", notes = "")
	@RequestMapping(value = "/correctionPint", method = RequestMethod.POST)
	public @ResponseBody WebCommonResponse<ResponseData> correctionPint(@RequestBody WebCommonRequest<CorrectionPintRequestData>  correctionBean) {
		
		//TODO 先进行数据规范校验,基本校验交给Hibernate Validator,其他在交易处理前校验;
		
		CorrectionPintRequest correctionRequest = new CorrectionPintRequest();
		correctionRequest.setAsynInd(correctionBean.getAsynInd());
		correctionRequest.setChannelId(correctionBean.getChannelId());
		correctionRequest.setChannelSign(correctionBean.getChannelSign());
		correctionRequest.setClearingDate(correctionBean.getClearingDate());
		correctionRequest.setRequestData(correctionBean.getRequestData());
		correctionRequest.setTimestamp(correctionBean.getTimestamp());
		correctionRequest.setTxnSerialNo(correctionBean.getTxnSerialNo());
		correctionRequest.setSvPrId(correctionBean.getSvPrId());
		correctionRequest.setAdditionalReqMap(correctionBean.getAdditionalReqMap());
		
		if(AsynInd.A.equals(correctionBean.getAsynInd())){
			//异步接口，调相应异步TransFlow执行业务处理,其主要区别在于相应的invoker通过MQ生产者发送消息
			scccAccountMqProducer.senderMsg4CorrectionPint(correctionRequest);
			return new WebCommonResponseBuilder<ResponseData>().build().setStatusCode(WebCommonResponse.DESC_SUCCESS).setStatusDesc(WebCommonResponse.DESC_SUCCESS);
		}
		else if(AsynInd.S.equals(correctionBean.getAsynInd())){
			//同步接口，调相应同步TransFlow执行业务处理，暂时不需要
			log.warn("目前该交易不支持同步接口");
			throw new ErrorMessageException(ErrorCode.Restricted, "目前该交易不支持同步接口 ");
		}
		else {
			throw new ErrorMessageException(ErrorCode.BadRequest, "异步接口标识不合法：A|异步 S|同步 ");
		}
	}
}
