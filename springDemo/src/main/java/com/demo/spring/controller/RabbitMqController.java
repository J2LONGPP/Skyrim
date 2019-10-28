package com.demo.spring.controller;

import com.demo.spring.entity.ActiveMqUser;
import com.demo.spring.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用控制器测试RabbitMQ服务和接收器
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {
    @Autowired
    private RabbitMQService rabbitMQService=null;

    //测试发送字符串消息
    @GetMapping("/msg")
    public Map<String,Object> sendMsg(String msg){
        rabbitMQService.sendMsg(msg);
        return resultMap("message",msg);
    }

    //测试发送用户信息
    @GetMapping("/user")
    public Map<String,Object> sendUser(ActiveMqUser user){
        rabbitMQService.sendUser(user);
        return resultMap("user",user);
    }

    private Map<String, Object> resultMap(String key, Object obj) {
        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put(key,obj);
        return result;
    }
}
