package com.demo.spring.websocket;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 定义WebSocket服务端站点
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 *
 * @ServerEndpoint("/ws")  表示让Spring创建WebSocket的服务端点，其中请求的地址为 '/ws'
 * @OnOpen 标注客户端打开WebSocket服务端点调用方法
 * @OnClose 标注客户端关闭WebSocket服务端点调用方法
 * @OnMessage 标注客户端发送消息，WebSocket服务端点调用方法
 * @OnError 标注客户端请求WebSocket服务端点发生异常调用方法
 */
@ServerEndpoint("/ws")
@Service
public class WebSocketServiceImpl {
    //静态变量，用来记录当前在线连接数，应该把它设计成线程安全的
    private static int onlineCount=0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServiceImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet=new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session=null;

    /**
     * 连接建立成功调用方法
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
      this.session=session;
      webSocketSet.add(this);//加入Set中
      addOnlineCount();//在线数加1
      System.out.println("有新连接加入！当前在线人数为："+getOnlineCount());
      sendMessage("有新的连接加入了！");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this); //从Set中删除
        subOnlineCount();//在线数减1
        System.out.println("有一连接关闭！当前在线人数为："+getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message  消息
     * @param session  会话
     */
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        System.out.println("来自客户端的消息："+message);
        //群发消息
        for (WebSocketServiceImpl item:webSocketSet){
            //获取当前用户名称
            String userName=item.getSession().getUserPrincipal().getName();
            System.out.println("当期用户名："+userName);
            item.sendMessage(message);
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message 客户端消息
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 返回在线数
     * @return
     */
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    //当连接数减少时
    private static synchronized void subOnlineCount() {
        WebSocketServiceImpl.onlineCount--;
    }


    private static synchronized void addOnlineCount() {
        WebSocketServiceImpl.onlineCount++;
    }
    public static void setOnlineCount(int onlineCount) {
        WebSocketServiceImpl.onlineCount = onlineCount;
    }

    public static CopyOnWriteArraySet<WebSocketServiceImpl> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet) {
        WebSocketServiceImpl.webSocketSet = webSocketSet;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
