package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * WebSocket子协议 STOMP控制器
 * @author long.yu
 * @date 2019-04-15
 * @verison 0.0.1
 * 使用@EnableWebSocketMessageBroker驱动Spring启用STOMP协议。Spring boot就会自动创建SimpMessagingTemplate对象，它是一个可以进行转发
 * 消息的模板，通过这个模板可以发送消息到特定的地址，甚至是限制给特定的用户发送消息。
 */
@Controller
@RequestMapping("/websocket2")
public class WebSocketStompController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate=null;

    //发送页面
    @GetMapping("/send")
    public String send(){
        return "websocket/send";
    }
    //接收页面
    @GetMapping("/receive")
    public String receive(){
        return "websocket/receive";
    }

    //对特定用户发送页面
    @GetMapping("/senduser")
    public String sendUser(){
        return "websocket/send-user";
    }
    //接收用户消息页面
    @GetMapping("/receiveuser")
    public String receiveUser(){
        return "websocket/receive-user";
    }

    //定义消息请求路径
    @MessageMapping("/send")
    //定义结果发送到特定路径
    @SendTo("/sub/chat")
    public String sendMsg(String value){
        return value;
    }

    //将消息发送给特定的用户
    @MessageMapping("/senduser")
//    @SendToUser("/queue/customer")
    public void sendToUser(Principal principal,String body){
        String srcUser=principal.getName();
        //解析用户消息
        String[] args=body.split(",");
        String desUser=args[0];
        String message="【"+srcUser+"】给你发来消息"+args[1];
        System.out.println("用户id:"+desUser);
        //发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser,"/message",message);
    }


}
