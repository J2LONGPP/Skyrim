package com.demo.spring.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * ActiveMQ Artemis生产者
 * @author long.yu
 * @date 2019-04-10
 * @version 0.0.1
 */
//@Component
public class ArtemisProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate=null;

    public void send(Destination destination, String message) {
        jmsMessagingTemplate.convertAndSend(destination,message+"from queue");
    }

//    @JmsListener(destination = "out.queue")
    public void consumeMessage(String text) {
        System.out.println("从out.queue队列中收到的回复信息为："+text);
    }
}
