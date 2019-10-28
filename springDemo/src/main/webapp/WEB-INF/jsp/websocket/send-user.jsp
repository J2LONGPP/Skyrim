<%--
  Created by IntelliJ IDEA.
  User: Raytine
  Date: 2019/4/18
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送用户消息界面</title>
    <script type="text/javascript" src="../plugin/jquery3.4.0/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/sockjs.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/stomp.min.js"></script>
</head>
<script type="text/javascript">
    var stompClient=null;
    //重置连接状态页面
    function setConnected(connected) {
        console.log(connected);
        $("#connect").attr({"disabled":connected});
        $("#disconnect").attr({"disabled":!connected});
        if(connected){
            $("#conversation").show();
        }else{
            $("#conversation").hide();
        }
        $("#response").html("");
    }
    //开启socket连接
    function connect() {
        //连接 /wsuser服务器
        var socket=new SockJS("../wsuser");
        //stomp客户端
        stompClient=Stomp.over(socket);
        stompClient.connect({},function (frame) {
            console.log("connect..."+frame);
            setConnected(true);
        })
    }
    //断开socket连接
    function disconnect() {
        if(stompClient!=null){
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }
    //向 /request/sendUser服务端发送消息
    function sendMsg() {
        var value=$("#message").val();
        var user=$("#user").val();
        //用户和消息组成的字符串
        var text=user+","+value;
        console.log("准备发送数据")
        console.log(text);
        stompClient.send("/senduser",{},text);
    }

</script>
<body>
<div>
    <div>
        <button id="connect" onclick="connect()">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect()">断开连接</button>
    </div>
    <div id="conversation">
        <p><label>发送给用户</label></p>
        <p><input type="text" id="user"></p>
        <p><label>发送的内容</label></p>
        <p><textarea id="message" rows="5"></textarea></p>
        <button id="sendMsg" onclick="sendMsg()">发送</button>
        <p id="response"></p>
    </div>
</div>
</body>
</html>
