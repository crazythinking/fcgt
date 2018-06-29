package net.engining.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

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
 * 核算异步Online交易的消息生产者
 * @author luxue
 *
 */
@Service
public class ScccAccountMqProducer {
	
	private static final Logger log = LoggerFactory.getLogger(ScccAccountMqProducer.class);

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	Queue scccAccountTransQueue;
	
	/**
	 * FIXME WebCommonRequest<Map<String, Object>> 这里map改为定义的Bean
	 * @param transferDataByKey
	 */
	public void senderMsg4UpDownLimetAmount(LimitRequest limitRequest){
		log.debug("将为授额|提降额记账交易发送消息到MQ，payload={}", JSON.toJSONString(limitRequest));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), limitRequest);
	}
	
	public void senderMsg4CostAccount(CostAccountRequest requestBean){
		log.debug("将费用收取记账交易发送消息到MQ，payload={}", JSON.toJSONString(requestBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), requestBean);
	}
	
	public void senderMsg4LoanAccount(LoanAccountRequest  requestBean){
		log.debug("将贷款发放记账交易发送消息到MQ，payload={}", JSON.toJSONString(requestBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), requestBean);
	}
	
	public void senderMsg4RefundsAccount(RefundsRequest  requestBean){
		log.debug("将退款记账交易发送消息到MQ，payload={}", JSON.toJSONString(requestBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), requestBean);
	}
	
	public void senderMsg4RepaymentAccount(RePaymentRequest  requestBean){
		log.debug("将还款记账交易发送消息到MQ，payload={}", JSON.toJSONString(requestBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), requestBean);
	}

	public void senderMsg4CorrectionRepayment(CorrectionRepaymentRequest  correctionBean){
		log.debug("将冲正还款记账交易发送消息到MQ，payload={}", JSON.toJSONString(correctionBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), correctionBean);
	}

	public void senderMsg4CorrectionInteAcc(CorrectionInteAccRequest  correctionBean){
		log.debug("将冲正利息计提发送消息到MQ，payload={}", JSON.toJSONString(correctionBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), correctionBean);
	}
	
	public void senderMsg4CorrectionPnitAcc(CorrectionPnitAccRequest  correctionBean){
		log.debug("将冲正罚息计提发送消息到MQ，payload={}", JSON.toJSONString(correctionBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), correctionBean);
	}

	public void senderMsg4CorrectionLbal(CorrectionTransfoRequest  correctionBean){
		log.debug("将冲正本金结转发送消息到MQ，payload={}", JSON.toJSONString(correctionBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), correctionBean);
	}

	public void senderMsg4CorrectionInte(CorrectionInteRequest  correctionBean){
		log.debug("将冲正利息结转发送消息到MQ，payload={}", JSON.toJSONString(correctionBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), correctionBean);
	}

	public void senderMsg4CorrectionPint(CorrectionPintRequest  correctionBean){
		log.debug("将冲正罚息结转发送消息到MQ，payload={}", JSON.toJSONString(correctionBean));
		rabbitTemplate.convertAndSend(scccAccountTransQueue.getName(), correctionBean);
	}
}
