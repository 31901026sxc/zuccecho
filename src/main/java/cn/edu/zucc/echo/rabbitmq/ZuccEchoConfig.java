package cn.edu.zucc.echo.rabbitmq;

import cn.edu.zucc.echo.utils.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengbin
 * @version 1.0
 * @date 2022/04/27 22:06
 */
@Configuration
public class ZuccEchoConfig {
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(Constants.EX_FANOUT);
    }

    @Bean
    public Queue fanoutNotifyQue() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue fanoutTrackQue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingNotify(FanoutExchange fanout,
                            Queue fanoutNotifyQue) {
        return BindingBuilder.bind(fanoutNotifyQue).to(fanout);
    }

    @Bean
    public Binding bindingTrack(FanoutExchange fanout,
                            Queue fanoutTrackQue) {
        return BindingBuilder.bind(fanoutTrackQue).to(fanout);
    }

    @Bean
    public FanoutTrackConsumer fanoutTrackConsumer() {
        return new FanoutTrackConsumer();
    }

    @Bean
    public FanoutNotifyConsumer fanoutNotifyConsumer() {
        return new FanoutNotifyConsumer();
    }

    @Bean
    public Queue queue() {
        return new Queue(Constants.QUE_SIMPLE);
    }

    @Bean
    public Queue queueWorkQueue() {
        return new Queue(Constants.QUE_WORK_QUEUE);
    }

    @Bean
    public WorkQueueConsumer workQueueConsumer01() {
        return new WorkQueueConsumer("Worker-01");
    }

    @Bean
    public WorkQueueConsumer workQueueConsumer02() {
        return new WorkQueueConsumer("Worker-02");
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(Constants.EX_DIRECT);
    }

    @Bean
    public Queue directFatalQue() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue directWarnQue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingFatal(DirectExchange direct,
                             Queue directFatalQue) {
        return BindingBuilder.bind(directFatalQue)
                .to(direct)
                .with(Constants.KEY_FATAL);
    }

    @Bean
    public Binding bindingWarn(DirectExchange direct,
                                Queue directWarnQue) {
        return BindingBuilder.bind(directWarnQue)
                .to(direct)
                .with(Constants.KEY_WARN);
    }

    @Bean
    public DirectFatalConsumer directFatalConsumer() {
        return new DirectFatalConsumer();
    }

    @Bean
    public DirectWarnConsumer directWarnConsumer() {
        return new DirectWarnConsumer();
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(Constants.EX_TOPIC);
    }

    @Bean
    public Queue topicTimeStatQue() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue topicCollegeStatQue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingTime(TopicExchange topic,
                                Queue topicTimeStatQue) {
        return BindingBuilder.bind(topicTimeStatQue)
                .to(topic)
                .with(Constants.TOPIC_TIME);
    }

    @Bean
    public Binding bindingColege(TopicExchange topic,
                               Queue topicCollegeStatQue) {
        return BindingBuilder.bind(topicCollegeStatQue)
                .to(topic)
                .with(Constants.TOPIC_COLLEGE);
    }

    @Bean
    public TopicTimeConsumer topicTimeConsumer() {
        return new TopicTimeConsumer();
    }

    @Bean
    public TopicCollegeConsumer topicCollegeConsumer() {
        return new TopicCollegeConsumer();
    }

    @Bean
    public DirectExchange rpc() {
        return new DirectExchange(Constants.EX_RPC);
    }

    @Bean
    public Queue rpcQue() {
        return new Queue(Constants.QUE_RPC_QUEUE);
    }

    @Bean
    public Binding bindingRpc(@Qualifier("rpc") DirectExchange rpc,
                           Queue rpcQue) {
        return BindingBuilder.bind(rpcQue)
                .to(rpc)
                .with(Constants.KEY_RPC);
    }

    @Bean
    public RPCConsumer rpcConsumer() {
        return new RPCConsumer();
    }
}

