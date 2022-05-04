package cn.edu.zucc.echo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@RabbitListener(queues = "#{directWarnQue.name}")
public class DirectWarnConsumer {
    private final Logger logger = LoggerFactory.getLogger(DirectWarnConsumer.class);
    private String name = "DirectWarnConsumer";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]DirectWarnConsumer handle warning.", this.name);
    }
}
