package cn.edu.zucc.echo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author pengbin
 * @version 1.0
 */
@RabbitListener(queues = "#{topicTimeStatQue.name}")
public class TopicTimeConsumer {
    private final Logger logger = LoggerFactory.getLogger(TopicTimeConsumer.class);
    private String name = "TopicTimeConsumer";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]TopicTimeConsumer do statistics by time.", this.name);
    }
}
