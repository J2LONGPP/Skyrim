package com.demo.spring.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 创建两个RabbitMQ队列
 * @author long.yu
 * @date 2019-04-14
 * @ersion 0.0.1
 * 这里SpringBoot的机制会自动注册这两个队列，所以不需要自己做进一步的绑定
 */
//@Component
public class RabbitMqQueue {

    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName=null;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userQueueName=null;

    @Bean
    public Queue createQueueMsg(){
        //创建字符串消息队列,boolean值代表是否持久化消息
        return new Queue(msgQueueName,true);
    }
    @Bean
    public Queue createQueueUser(){
        //创建用户消息队列,boolean值代表是否持久化消息
        return new Queue(userQueueName,true);
    }
}
