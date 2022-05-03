package cn.edu.zucc.echo.rabbitmq;

import cn.edu.zucc.echo.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author pengbin
 * @version 1.0
 */
@RabbitListener(queues = {Constants.QUE_WORK_QUEUE})
public class WorkQueueConsumer {
    private final Logger logger = LoggerFactory.getLogger(WorkQueueConsumer.class);
    private String name;

    public WorkQueueConsumer(String name) {
        this.name = name;
    }

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]WorkQueueConsumer got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]WorkQueueConsumer do update status.", this.name);
    }
}
