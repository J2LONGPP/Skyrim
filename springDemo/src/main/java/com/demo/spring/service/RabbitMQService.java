package com.demo.spring.service;

import com.demo.spring.entity.ActiveMqUser;

/**
 * 定义RabbitMQ服务接口
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 */
public interface RabbitMQService {
    //发送字符串消息
    void sendMsg(String msg);
    //发送用户消息
    void sendUser(ActiveMqUser user);
}
