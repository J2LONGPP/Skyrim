package com.demo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocket控制器
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {
    //跳转到websocket页面
    @GetMapping("/test")
    public String websocket(){
        return "websocket/websocket";
    }
}
