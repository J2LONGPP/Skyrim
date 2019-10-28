package com.demo.spring.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

/**
 * 配置(WebSocket子协议)STOMP的服务端点和请求订阅前缀
 * @author long.yu
 * @date 2019-04-15
 * @version 0.0.1
 * 并不是所有的浏览器都能够支持WebSocket协议，为了使WebSocket的应用能够兼容那些不支持的浏览器，可以使用STOMP协议进行处理。首先需要在配置文件中加入注解
 * @EnableWebSocketMessageBroker 这个注解将会启动WebSocket下的子协议STOMP。
 * 为了配置这个协议，可以实现Spring提供给接口WebSocketMessageBrokerConfigurer。
 * 为了更简单，Spring还提供了这个接口的空实现的抽象类AbstractWebSocketMessageBrokerConfigurer,通过覆盖它所定义的方法即可。
 */
//使用STOMP协议
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig2 implements WebSocketMessageBrokerConfigurer {

    //注册服务器端点
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //增加一个聊天服务端点
        registry.addEndpoint("/socket").withSockJS();//SocketJS是一个第三方关于支持WebSocket请求的JavaScript框架
        //增加一个用户服务端点
        registry.addEndpoint("/wsuser").withSockJS();
    }
    //定义服务器端点请求和订阅前缀
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端订阅路径前缀
        registry.enableSimpleBroker("/sub","/user");
        //服务端点请求前缀
//        registry.setApplicationDestinationPrefixes("/request");
    }
}
