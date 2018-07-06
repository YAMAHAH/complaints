package com.axon.axondemo.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("publishService")
public class PublishService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String exchange, String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }
//    如果消息没有到exchange,则confirm回调,ack=false
//
//    如果消息到达exchange,则confirm回调,ack=true
//
//    exchange到queue成功,则不回调return
//
//    exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
}

