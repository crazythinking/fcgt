package net.engining.gateway.dispacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;

import net.engining.gateway.entity.DemoMessage;
import net.engining.gateway.send.callback.MessageConfirmCallback;


/**
 * 简易授信申请分发器. 从简易授信审队列获取申请件，分发到简易授信申请处理器.
 */
public class DemoDispatcher implements ChannelAwareMessageListener {

	private static final Logger logger = LoggerFactory.getLogger(DemoDispatcher.class);
	
	@Autowired
	private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

	/**
	 * 分发简易授信申请件.
	 * <p/>
	 * 1. 从RMQ取消息 2. 消息幂等检查 3. 更新inbound流水 未处理-->处理中; 更新申请错误队列信息 未处理-->处理中.
	 * <p/>
	 * 4. 启动简易授信申请处理器. 5. 发送ACK确认
	 *
	 * @param message
	 *            队列消息
	 * @param channel
	 * @throws Exception
	 */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		logger.info("<<=====mq开始分发demo[begin]...");

		try {
			// 1. 从RMQ取消息
			DemoMessage simpleCreditApplyMessage = (DemoMessage) jackson2JsonMessageConverter
					.fromMessage(message);

			logger.info("<<=====mq分发demo时,处理成功[end]." + simpleCreditApplyMessage.toString());
		} catch (Exception e) {
			logger.error("<<=====mq分发demo时,处理失败[end]." + e);
		} finally {
			// 5. 发送ACK确认，避免队列消息堵塞.
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}
	}

}
