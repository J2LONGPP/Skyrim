<%--
  Created by IntelliJ IDEA.
  User: Raytine
  Date: 2019/4/15
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送WebSocket请求的客户端(STOMP)</title>
    <script type="text/javascript" src="../plugin/jquery3.4.0/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/sockjs.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/stomp.min.js"></script>
</head>
<script type="text/javascript">
    var stompClient = null;
    //设置连接
    function setConnected(connected) {
        $("#connect").attr({"disabled":connected});
        $("#disconnect").attr({"disabled":!connected});
        if(connected){
            $("#conversation").show();
        }else{
            $("#conversation").hide();
        }
    }
    //开启socket连接
    function connect() {
        //定义服务器的端点
        var socket=new SockJS("../socket");
        //定义stomp客户端
        stompClient=Stomp.over(socket);
        //连接服务器端点
        stompClient.connect({},function(frame){
            //建立连接后的回调
            setConnected(true);
        });
    }
    //断开socket连接
    function disconnect() {
        if(stompClient!=null){
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }
    //向/request/send服务器发送消息
    function sendMsg() {
        var value=$("#message").val();
        //发送消息到“/request/send”，其中/request是服务器定义的前缀
        //而/send则是@MessageMapping所配置的路径
        stompClient.send("/send",{},value);
    }
    connect();
</script>
<body>
<div>
<div>
    <button id="connect" onclick="connect()">连接</button>
    <button id="disconnect" disabled="disabled" onclick="disconnect()">断开连接</button>
</div>
    <div id="conversation">
        <p>
            <label>发送的内容</label>
        </p>
        <p>
            <textarea id="message" rows="5"></textarea>
        </p>
        <button id="sendMsg" onclick="sendMsg()">Send</button>
        <p id="response"></p>
    </div>
</div>
</body>
</html>
