package com.demo.spring.controller;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import com.demo.spring.activemq.ArtemisProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * Artemis 消息推送测试
 * @author long.yu
 * @date 2019-04-10
 * @version 0.0.1
 */
@RestController
@RequestMapping("/artemis")
public class ArtemisController {

//    @Autowired
    private ArtemisProducer producer=null;

    @GetMapping("/mytest")
    public String mqtest(){
        Destination destination=new ActiveMQQueue("mytest.queue");
        producer.send(destination,"You have a Message");
        return "OK";
    }
}
