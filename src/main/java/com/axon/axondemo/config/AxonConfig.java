package com.axon.axondemo.config;

import com.axon.axondemo.commandside.aggregate.transfer.MoneyTransferSaga;
import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.transaction.Transactional;

@Configuration
public class AxonConfig {
    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("Complaints").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("Complaints").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Bean
    public Exchange exchange2() {
        return ExchangeBuilder.fanoutExchange("Complaints2").build();
    }

    @Bean
    public Queue queue2() {
        return QueueBuilder.durable("Complaints2").build();
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange2()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(exchange());
        admin.declareQueue(queue());
        admin.declareBinding(binding());

        admin.declareExchange(exchange2());
        admin.declareQueue(queue2());
        admin.declareBinding(binding2());
    }

    @Bean
    public SagaConfiguration<MoneyTransferSaga> moneyTransferSagaSagaConfiguration(Serializer serializer){
        SagaConfiguration<MoneyTransferSaga> sagaConfiguration = SagaConfiguration.subscribingSagaManager(MoneyTransferSaga.class,
                c-> statisticsQueue(serializer));
        //sagaConfiguration.registerHandlerInterceptor(c->transactionManagingInterceptor());
        return sagaConfiguration;
    }

    @Bean
    public SpringAMQPMessageSource statisticsQueue(Serializer serializer) {
        return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {
            @RabbitListener(queues = "Complaints2")
            @Transactional
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                super.onMessage(message, channel);
               // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); 手工确认
                System.out.println("saga msg queue ok");
            }
        };
    }

//    @Bean
//    public TransactionManagingInterceptor transactionManagingInterceptor(){
//        return new TransactionManagingInterceptor(new SpringTransactionManager(transactionManager));
//    }

//    @Bean
//    public EventHandlingConfiguration eventHandlingConfiguration(){
//        return new EventHandlingConfiguration();
//    }

//    @Autowired
//    public void configure(EventHandlingConfiguration config) {
//        config.registerTrackingProcessor("com.axon.axondemo.queryside");
//    }
}
