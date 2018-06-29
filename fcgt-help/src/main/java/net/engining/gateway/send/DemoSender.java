package net.engining.gateway.send;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import net.engining.gateway.entity.DemoMessage;

/**
 * 简易授信申请发送至RMQ.
 */
@Service
public class DemoSender {

	private static final Logger logger = LoggerFactory.getLogger(DemoSender.class);

	@Resource(name = "template_demo")
	private RabbitTemplate rabbitTemplate;

	/**
	 * 逐笔简易授信申请发送至RMQ.
	 * 
	 * <pre>
	 *   1. 报文检查.
	 *   2. 发送至MQ
	 * </pre>
	 */
	public boolean sendMessageToRMQ(DemoMessage demoMessage) {
		boolean flag = false;

		logger.info("CE受理IBCB简易授信申请时，开始发送至授信申请队列[begin]...");
		try {

			rabbitTemplate.convertAndSend("ce_exchange_demo", "ce_queue_demo_key", demoMessage);
			flag = true;
			logger.info("CE受理IBCB简易授信申请时，发送至授信申请队列成功[end].");
		} catch (AmqpException e) {
			logger.error("CE受理IBCB简易授信申请时，发送至授信申请队列失败[end].{}" + e);
		}
		return flag;
	}

}
