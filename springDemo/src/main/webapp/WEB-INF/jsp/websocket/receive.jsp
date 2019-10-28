<%--
  Created by IntelliJ IDEA.
  User: Raytine
  Date: 2019/4/17
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户端订阅消息</title>
    <script type="text/javascript" src="../plugin/jquery3.4.0/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/sockjs.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/stomp.min.js"></script>
</head>
<script type="text/javascript">
    var noticeSocket = function () {
        //连接服务器端点
        var s = new SockJS("../socket");
        //客户端
        var stompClient=Stomp.over(s);
        stompClient.connect({},function () {
            console.log("notice socket connected!");
            //订阅消息地址
            stompClient.subscribe("/sub/chat",function (data) {
                console.log("receive接收到的数据")
                console.log(data);
                $("#receive").html(data.body);
            })
        })
    }
    noticeSocket();
</script>
<body>
<h1><span id="receive">等待接收消息</span></h1>
</body>
</html>
