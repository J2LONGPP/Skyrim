package com.demo.spring.service.impl;

import com.demo.spring.entity.ActiveMqUser;
import com.demo.spring.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ服务接口实现类
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 * 实现ConfirmCallback接口，这样可以回调
 * 配置项 publisher-confirms: true意味着发送消息方可以监听发送消息到消费者端是否成功，如果成功
 * 它会根据设置的类进行回调
 */
@Service
public class RabbitMQServiceImpl implements ConfirmCallback,RabbitMQService {

    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgRouting=null;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userRouting=null;

    //注入由spring boot自动配置的RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate=null;

    //发送消息
    @Override
    public void sendMsg(String msg) {
        System.out.println("发送消息："+msg);
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        //发送消息，通过msgRouting确定队列
        rabbitTemplate.convertAndSend(msgRouting,msg);
    }

    //发送用户
    @Override
    public void sendUser(ActiveMqUser user) {
        System.out.println("发送用户消息："+user);
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(userRouting,user);
    }

    //回调确认方法
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("消息成功消费");
        }else{
            System.out.println("消息消费失败："+cause);
        }
    }
}
