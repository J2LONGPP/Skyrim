package com.demo.spring.service.impl;

import com.demo.spring.entity.ActiveMqUser;
import com.demo.spring.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * ActiveMq服务实现类
 * @author long.yu
 * @date 2019-04-11
 * @version 0.0.1
 * convertAndSend 是一个发送消息的方法，首先是convert，顾名思义就是转换，在默认情况下，JmsTemplate会提供一个
 * SimpleMessageConvert去提供转换规则，它实现了MessageConvert接口，如果要使用其他的序列化器，如SerializerMessageConvert（序列化消息转换器）
 * 或者Jackson2JsonMessageConvert（JSON消息转化器）,只要使用JmsTemplate的setMessageConvert方法进行设置即可
 * 不过一般情况下SimpleMessageConvert已经足够用了
 */
@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    //注入由spring boot自动生产的jmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate=null;

    //自定义地址
    private static final String myDestination="my-destination";

    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息【"+message+"】");
        jmsTemplate.convertAndSend(message);
        //自定义发送地址
        //jmsTemplate.convertAndSend("your-destination",message);
    }

    @Override
    //使用注解 监听地址发过来的消息
//    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        System.out.println("接收的消息：【"+message+"】");
    }

    @Override
    public void sendUser(ActiveMqUser user) {
        System.out.println("发送消息【"+user+"】");
        //使用自定义地址发送对象
        jmsTemplate.convertAndSend(myDestination,user);
    }

    @Override
//    @JmsListener(destination = myDestination)
    public void receiveUser(ActiveMqUser user) {
        System.out.println("接收的消息：【"+user+"】");
    }
}
