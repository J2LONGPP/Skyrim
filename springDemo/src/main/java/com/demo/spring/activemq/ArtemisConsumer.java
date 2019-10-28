package com.demo.spring.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * ActiveMQ Artemis消费者
 * @author long.yu
 * @date 2019-04-10
 * @version 0.0.1
 */
//@Component
public class ArtemisConsumer {

//    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer收到的信息为："+text);
        return "return message"+text;
    }
}
