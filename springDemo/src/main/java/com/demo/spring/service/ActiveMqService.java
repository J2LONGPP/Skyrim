package com.demo.spring.service;

import com.demo.spring.entity.ActiveMqUser;

/**
 * 定义ActiveMQ服务接口
 * @author long.yu
 * @date 2019-04-11
 * @version 0.0.1
 */
public interface ActiveMqService {
    //发送消息
    void sendMsg(String message);
    //接口消息
    void receiveMsg(String message);

    //发送POJO
    void sendUser(ActiveMqUser user);
    //接收POJO
    void receiveUser(ActiveMqUser user);
}
