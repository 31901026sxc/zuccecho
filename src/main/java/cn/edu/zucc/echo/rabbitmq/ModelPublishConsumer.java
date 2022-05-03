package cn.edu.zucc.echo.rabbitmq;

import cn.edu.zucc.echo.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author pengbin
 * @version 1.0
 */
@Component
@RabbitListener(queues = {Constants.QUE_SIMPLE})
public class ModelPublishConsumer {
    private final Logger logger = LoggerFactory.getLogger(ModelPublishConsumer.class);

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("send model publish notice to teachers.notice[{}]", msg.stringfy());
    }
}
