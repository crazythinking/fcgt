package net.engining.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.engining.control.sdk.FlowTransProcessorTemplate;
import net.engining.gateway.fts.flow.sdk.T001UpDownLimitRequest;
import net.engining.gateway.fts.flow.sdk.T001UpDownLimitResponse;
import net.engining.gateway.fts.flow.sdk.T002CostAccountRequest;
import net.engining.gateway.fts.flow.sdk.T002CostAccountResponse;
import net.engining.gateway.fts.flow.sdk.T003LoanAccountRequest;
import net.engining.gateway.fts.flow.sdk.T003LoanAccountResponse;
import net.engining.gateway.fts.flow.sdk.T004RefundsAccountRequest;
import net.engining.gateway.fts.flow.sdk.T004RefundsAccountResponse;
import net.engining.gateway.fts.flow.sdk.T005RepaymentAccountRequest;
import net.engining.gateway.fts.flow.sdk.T005RepaymentAccountResponse;
import net.engining.gateway.fts.flow.sdk.T006CorrectionRepaymentRequest;
import net.engining.gateway.fts.flow.sdk.T006CorrectionRepaymentResponse;
import net.engining.gateway.fts.flow.sdk.T007CorrectionInteAccRequest;
import net.engining.gateway.fts.flow.sdk.T007CorrectionInteAccResponse;
import net.engining.gateway.fts.flow.sdk.T008CorrectionLbalRequest;
import net.engining.gateway.fts.flow.sdk.T008CorrectionLbalResponse;
import net.engining.gateway.fts.flow.sdk.T009CorrectionPnitAccRequest;
import net.engining.gateway.fts.flow.sdk.T009CorrectionPnitAccResponse;
import net.engining.gateway.fts.flow.sdk.T010CorrectionInteRequest;
import net.engining.gateway.fts.flow.sdk.T010CorrectionInteResponse;
import net.engining.gateway.fts.flow.sdk.T011CorrectionPintRequest;
import net.engining.gateway.fts.flow.sdk.T011CorrectionPintResponse;
import net.engining.gateway.fts.sdk.bean.inteAcc.CorrectionInteAccRequest;
import net.engining.gateway.fts.sdk.bean.correction.CorrectionRepaymentRequest;
import net.engining.gateway.fts.sdk.bean.cost.CostAccountRequest;
import net.engining.gateway.fts.sdk.bean.init.CorrectionInteRequest;
import net.engining.gateway.fts.sdk.bean.limit.LimitRequest;
import net.engining.gateway.fts.sdk.bean.loan.LoanAccountRequest;
import net.engining.gateway.fts.sdk.bean.pnit.CorrectionPintRequest;
import net.engining.gateway.fts.sdk.bean.pnitAcc.CorrectionPnitAccRequest;
import net.engining.gateway.fts.sdk.bean.refunds.RefundsRequest;
import net.engining.gateway.fts.sdk.bean.repayment.RePaymentRequest;
import net.engining.gateway.fts.sdk.bean.transfer.CorrectionTransfoRequest;

/**
 * 核算异步Online交易的消息消费者，消费消息并调用相应TransFlow
 * @author luxue
 *
 */
@Service
@RabbitListener(containerFactory="rabbitListenerContainerFactory", queues="sccc.account.trans.queue")
public class ScccAccountMqConsumer {

	private static final Logger log = LoggerFactory.getLogger(ScccAccountMqConsumer.class);
	
	@Autowired
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory;
	
	@Autowired
	FlowTransProcessorTemplate flowTransProcessorTemplate;
	
//	@RabbitListener(containerFactory="rabbitListenerContainerFactory", queues="sccc.account.trans.queue")
	@RabbitHandler
	public void handle4UpDownLimetAmount(LimitRequest msg){
		//TODO 从msg设置request
		T001UpDownLimitRequest t001UpDownLimitRequest = new T001UpDownLimitRequest();
		t001UpDownLimitRequest.setAsynInd(msg.getAsynInd());
//		t001UpDownLimitRequest.setTransCode(bean.gett);
		t001UpDownLimitRequest.setSvPrId(msg.getSvPrId());
		t001UpDownLimitRequest.setChannel(msg.getChannelId());
		t001UpDownLimitRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t001UpDownLimitRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t001UpDownLimitRequest.setAsynInd(msg.getAsynInd());
		t001UpDownLimitRequest.setChannelSignToken(msg.getChannelSign());
		t001UpDownLimitRequest.setTargetBizDate(msg.getClearingDate());
		t001UpDownLimitRequest.setTxnDateTime(msg.getTimestamp());
		
		//调用相应授额|提降额记账交易
		T001UpDownLimitResponse rsp = flowTransProcessorTemplate.process(t001UpDownLimitRequest, T001UpDownLimitResponse.class);
	}
	@RabbitHandler
	public void handle4CostAccount(CostAccountRequest msg){
		//TODO 从msg设置request
		T002CostAccountRequest t002CostAccountRequest = new T002CostAccountRequest();
		t002CostAccountRequest.setAsynInd(msg.getAsynInd());
//		t002CostAccountRequest.setTransCode(bean.gett);
		t002CostAccountRequest.setSvPrId(msg.getSvPrId());
		t002CostAccountRequest.setChannel(msg.getChannelId());
		t002CostAccountRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t002CostAccountRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t002CostAccountRequest.setAsynInd(msg.getAsynInd());
		t002CostAccountRequest.setChannelSignToken(msg.getChannelSign());
		t002CostAccountRequest.setTargetBizDate(msg.getClearingDate());
		t002CostAccountRequest.setTxnDateTime(msg.getTimestamp());
		
		//费用收取记账交易
		T002CostAccountResponse rsp = flowTransProcessorTemplate.process(t002CostAccountRequest, T002CostAccountResponse.class);
	}
	@RabbitHandler
	public void handle4LoanAccount(LoanAccountRequest msg){
		//TODO 从msg设置request
		T003LoanAccountRequest t003LoanAccountRequest = new T003LoanAccountRequest();
		t003LoanAccountRequest.setAsynInd(msg.getAsynInd());
//		t003LoanAccountRequest.setTransCode(bean.gett);
		t003LoanAccountRequest.setSvPrId(msg.getSvPrId());
		t003LoanAccountRequest.setChannel(msg.getChannelId());
		t003LoanAccountRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t003LoanAccountRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t003LoanAccountRequest.setAsynInd(msg.getAsynInd());
		t003LoanAccountRequest.setChannelSignToken(msg.getChannelSign());
		t003LoanAccountRequest.setTargetBizDate(msg.getClearingDate());
		t003LoanAccountRequest.setTxnDateTime(msg.getTimestamp());
		
		//贷款发放记账交易
		T003LoanAccountResponse rsp = flowTransProcessorTemplate.process(t003LoanAccountRequest, T003LoanAccountResponse.class);
	}
	@RabbitHandler
	public void handle4RefundsAccount(RefundsRequest msg){
		//TODO 从msg设置request
		T004RefundsAccountRequest t004RefundsAccountRequest = new T004RefundsAccountRequest();
		t004RefundsAccountRequest.setAsynInd(msg.getAsynInd());
//		t004RefundsAccountRequest.setTransCode(bean.gett);
		t004RefundsAccountRequest.setSvPrId(msg.getSvPrId());
		t004RefundsAccountRequest.setChannel(msg.getChannelId());
		t004RefundsAccountRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t004RefundsAccountRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t004RefundsAccountRequest.setAsynInd(msg.getAsynInd());
		t004RefundsAccountRequest.setChannelSignToken(msg.getChannelSign());
		t004RefundsAccountRequest.setTargetBizDate(msg.getClearingDate());
		t004RefundsAccountRequest.setTxnDateTime(msg.getTimestamp());
		
		//贷款发放记账交易
		T004RefundsAccountResponse rsp = flowTransProcessorTemplate.process(t004RefundsAccountRequest, T004RefundsAccountResponse.class);
	}
	
	@RabbitHandler
	public void handle4RepaymentAccount(RePaymentRequest msg){
		//TODO 从msg设置request
		T005RepaymentAccountRequest t005RepaymentAccountRequest = new T005RepaymentAccountRequest();
		t005RepaymentAccountRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t005RepaymentAccountRequest.setSvPrId(msg.getSvPrId());
		t005RepaymentAccountRequest.setChannel(msg.getChannelId());
		t005RepaymentAccountRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t005RepaymentAccountRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t005RepaymentAccountRequest.setAsynInd(msg.getAsynInd());
		t005RepaymentAccountRequest.setChannelSignToken(msg.getChannelSign());
		t005RepaymentAccountRequest.setTargetBizDate(msg.getClearingDate());
		t005RepaymentAccountRequest.setTxnDateTime(msg.getTimestamp());
		
		//还款发放记账交易
		T005RepaymentAccountResponse rsp = flowTransProcessorTemplate.process(t005RepaymentAccountRequest, T005RepaymentAccountResponse.class);
	}

	@RabbitHandler
	public void handle4CorrectionRepayment(CorrectionRepaymentRequest msg){
		//TODO 从msg设置request
		T006CorrectionRepaymentRequest t006CorrectionRepaymentRequest = new T006CorrectionRepaymentRequest();
		t006CorrectionRepaymentRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t006CorrectionRepaymentRequest.setSvPrId(msg.getSvPrId());
		t006CorrectionRepaymentRequest.setChannel(msg.getChannelId());
		t006CorrectionRepaymentRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t006CorrectionRepaymentRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t006CorrectionRepaymentRequest.setAsynInd(msg.getAsynInd());
		t006CorrectionRepaymentRequest.setChannelSignToken(msg.getChannelSign());
		t006CorrectionRepaymentRequest.setTargetBizDate(msg.getClearingDate());
		t006CorrectionRepaymentRequest.setTxnDateTime(msg.getTimestamp());
		
		//冲正还款发放记账交易
		T006CorrectionRepaymentResponse rsp = flowTransProcessorTemplate.process(t006CorrectionRepaymentRequest, T006CorrectionRepaymentResponse.class);
	}
	
	@RabbitHandler
	public void handle4CorrectionInteAcc(CorrectionInteAccRequest msg){
		//TODO 从msg设置request
		T007CorrectionInteAccRequest t007CorrectionInteAccRequest = new T007CorrectionInteAccRequest();
		t007CorrectionInteAccRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t007CorrectionInteAccRequest.setSvPrId(msg.getSvPrId());
		t007CorrectionInteAccRequest.setChannel(msg.getChannelId());
		t007CorrectionInteAccRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t007CorrectionInteAccRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t007CorrectionInteAccRequest.setAsynInd(msg.getAsynInd());
		t007CorrectionInteAccRequest.setChannelSignToken(msg.getChannelSign());
		t007CorrectionInteAccRequest.setTargetBizDate(msg.getClearingDate());
		t007CorrectionInteAccRequest.setTxnDateTime(msg.getTimestamp());
		
		//冲正利息计提
		T007CorrectionInteAccResponse rsp = flowTransProcessorTemplate.process(t007CorrectionInteAccRequest, T007CorrectionInteAccResponse.class);
	}
	
	@RabbitHandler
	public void handle4CorrectionPnitAcc(CorrectionPnitAccRequest msg){
		//TODO 从msg设置request
		T009CorrectionPnitAccRequest t009CorrectionPnitAccRequest = new T009CorrectionPnitAccRequest();
		t009CorrectionPnitAccRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t009CorrectionPnitAccRequest.setSvPrId(msg.getSvPrId());
		t009CorrectionPnitAccRequest.setChannel(msg.getChannelId());
		t009CorrectionPnitAccRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t009CorrectionPnitAccRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t009CorrectionPnitAccRequest.setAsynInd(msg.getAsynInd());
		t009CorrectionPnitAccRequest.setChannelSignToken(msg.getChannelSign());
		t009CorrectionPnitAccRequest.setTargetBizDate(msg.getClearingDate());
		t009CorrectionPnitAccRequest.setTxnDateTime(msg.getTimestamp());
		
		//冲正罚息计提
		T009CorrectionPnitAccResponse rsp = flowTransProcessorTemplate.process(t009CorrectionPnitAccRequest, T009CorrectionPnitAccResponse.class);
	}
	
	@RabbitHandler
	public void handle4CorrectionLbal(CorrectionTransfoRequest msg){
		//TODO 从msg设置request
		T008CorrectionLbalRequest t008CorrectionLbalRequest = new T008CorrectionLbalRequest();
		t008CorrectionLbalRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t008CorrectionLbalRequest.setSvPrId(msg.getSvPrId());
		t008CorrectionLbalRequest.setChannel(msg.getChannelId());
		t008CorrectionLbalRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t008CorrectionLbalRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t008CorrectionLbalRequest.setAsynInd(msg.getAsynInd());
		t008CorrectionLbalRequest.setChannelSignToken(msg.getChannelSign());
		t008CorrectionLbalRequest.setTargetBizDate(msg.getClearingDate());
		t008CorrectionLbalRequest.setTxnDateTime(msg.getTimestamp());
		
		//冲正本金结转
		T008CorrectionLbalResponse rsp = flowTransProcessorTemplate.process(t008CorrectionLbalRequest, T008CorrectionLbalResponse.class);
	}
	
	@RabbitHandler
	public void handle4CorrectionInte(CorrectionInteRequest msg){
		//TODO 从msg设置request
		T010CorrectionInteRequest t010CorrectionInteRequest = new T010CorrectionInteRequest();
		t010CorrectionInteRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t010CorrectionInteRequest.setSvPrId(msg.getSvPrId());
		t010CorrectionInteRequest.setChannel(msg.getChannelId());
		t010CorrectionInteRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t010CorrectionInteRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t010CorrectionInteRequest.setAsynInd(msg.getAsynInd());
		t010CorrectionInteRequest.setChannelSignToken(msg.getChannelSign());
		t010CorrectionInteRequest.setTargetBizDate(msg.getClearingDate());
		t010CorrectionInteRequest.setTxnDateTime(msg.getTimestamp());
		
		//冲正利息结转
		T010CorrectionInteResponse rsp = flowTransProcessorTemplate.process(t010CorrectionInteRequest, T010CorrectionInteResponse.class);
	}
	
	@RabbitHandler
	public void handle4CorrectionPint(CorrectionPintRequest msg){
		//TODO 从msg设置request
		T011CorrectionPintRequest t011CorrectionPintRequest = new T011CorrectionPintRequest();
		t011CorrectionPintRequest.setAsynInd(msg.getAsynInd());
//		t005RepaymentAccountRequest.setTransCode(bean.gett);
		t011CorrectionPintRequest.setSvPrId(msg.getSvPrId());
		t011CorrectionPintRequest.setChannel(msg.getChannelId());
		t011CorrectionPintRequest.setOnlineData(JSON.toJSONString(msg.getRequestData()));
		t011CorrectionPintRequest.setChannelRequestSeq(msg.getTxnSerialNo());
		t011CorrectionPintRequest.setAsynInd(msg.getAsynInd());
		t011CorrectionPintRequest.setChannelSignToken(msg.getChannelSign());
		t011CorrectionPintRequest.setTargetBizDate(msg.getClearingDate());
		t011CorrectionPintRequest.setTxnDateTime(msg.getTimestamp());
		
		//冲正罚息结转
		T011CorrectionPintResponse rsp = flowTransProcessorTemplate.process(t011CorrectionPintRequest, T011CorrectionPintResponse.class);
	}
}
