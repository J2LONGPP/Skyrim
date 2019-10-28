package com.demo.spring.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 自定义WebSocket服务端点配置
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 * WebSocket协议是 基于TCP的一种新的网络协议，它实现了浏览器与服务器全双工通信，允许服务器主动发送信息给客户端
 * 这样就可以实现从客户端发送消息到服务器，而服务器又可以转发给客户端，这样就能够实现客户端之间的交互，对于WebSocket
 * 的开发，Spring也提供了良好的支持，目前有很多浏览器实现了WebSsocket协议，但是依旧存在着很多浏览器没有实现该协议
 * 为了兼容那些没有实现该协议的浏览器，往往还需要通过STOMP协议来完成这写兼容
 *
 * 有了这个Bean，就可以使用@ServerEndpoint定义一个端点服务类，在这个站点服务类中，还可以定义WebSocket的打开、关闭、错误和发送消息的方法。
 */
@Configuration
public class WebSocketConfig {
    //创建服务器端点
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
