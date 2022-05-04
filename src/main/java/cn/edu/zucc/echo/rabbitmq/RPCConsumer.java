package cn.edu.zucc.echo.rabbitmq;

import cn.edu.zucc.echo.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@RabbitListener(queues = {Constants.QUE_RPC_QUEUE})
public class RPCConsumer {
    private final Logger logger = LoggerFactory.getLogger(RPCConsumer.class);
    private String name = "RPCConsumer";

    @RabbitHandler
    public String handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]RPCConsumer send msg to user and wait...", this.name);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.warn("[{}]RPCConsumer confirmed.", this.name);
        return "confirmed";
    }
}
