package cn.edu.zucc.echo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@RabbitListener(queues = "#{fanoutNotifyQue.name}")
public class FanoutNotifyConsumer {
    private final Logger logger = LoggerFactory.getLogger(FanoutNotifyConsumer.class);
    private String name = "Fanout-Notify";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]FanoutTrackConsumer do notify.", this.name);
    }
}
