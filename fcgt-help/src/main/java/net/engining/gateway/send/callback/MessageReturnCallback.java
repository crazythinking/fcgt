package net.engining.gateway.send.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;


/**
 *
 */
public class MessageReturnCallback implements ReturnCallback {
    //private static final Logger logger = LoggerFactory.getLogger(MessageReturnCallback.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        //重新发布
        //RepublishMessageRecoverer recoverer = new RepublishMessageRecoverer(errorTemplate,"errorExchange", "errorRoutingKey");
    }
}
