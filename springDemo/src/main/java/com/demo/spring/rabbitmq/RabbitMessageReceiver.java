package com.demo.spring.rabbitmq;

import com.demo.spring.entity.ActiveMqUser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * RabbitMQ接收器
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 */
public class RabbitMessageReceiver {
    //定义监听字符串队列名称
    @RabbitListener(queues = "${rabbitmq.queue.msg}")
    public void receiveMsg(String msg){
        System.out.println("收到的消息："+msg);
    }
    //定义监听用户队列名称
    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void receiveUser(ActiveMqUser user){
        System.out.println("收到用户信息："+user);
    }
}
