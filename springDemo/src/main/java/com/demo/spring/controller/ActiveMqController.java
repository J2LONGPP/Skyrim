package com.demo.spring.controller;

import com.demo.spring.entity.ActiveMqUser;
import com.demo.spring.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ActiveMQ控制器
 * @author long.yu
 * @date 2019-04-11
 * @version 0.0.1
 */
@RestController
@RequestMapping("/activemq")
public class ActiveMqController {

    @Autowired
    private ActiveMqService activeMqService=null;

    //测试普通消息的发送
    @GetMapping("/msg")
    public Map<String,Object> msg(String msg){
        activeMqService.sendMsg(msg);
        return result(true,msg);
    }

    //测试ActiveMqUser对象的发送
    @GetMapping("/user")
    public Map<String,Object> sendUser(Long id,String msg,String note){
        ActiveMqUser user=new ActiveMqUser(id,msg,note);
        activeMqService.sendUser(user);
        return result(true,user);
    }

    private Map<String, Object> result(boolean success, Object msg) {
        Map<String,Object> result=new HashMap<>();
        result.put("success",success);
        result.put("message",msg);
        return result;
    }

}
