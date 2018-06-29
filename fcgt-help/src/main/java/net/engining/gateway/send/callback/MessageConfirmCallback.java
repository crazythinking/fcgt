package net.engining.gateway.send.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 消息确认, 消息send成功后, 服务器会告知客户端是否接收到消息[ACK]。
 * 一般会发生在exchange连接断掉的时候，消息确认中需要处理那些失败的消息.
 */
public class MessageConfirmCallback implements ConfirmCallback {
	private static final Logger logger = LoggerFactory.getLogger(MessageConfirmCallback.class);

	/**
	 * Broker
	 * 
	 * @param correlationData
	 * @param ack
	 * @param cause
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		logger.info("消息确认, ACK:" + ack);

		// 如果消息没有收到, 需要持久化这些失败的消息
		if (!ack) {
			logger.info("=====>>消息发送到MQ失败！");
			// throw new BusinessException("消息发送到MQ失败:" + cause);
		} else {
			logger.info("=====>>消息发送到MQ成功！");
		}
	}

}
