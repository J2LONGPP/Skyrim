<%--
  Created by IntelliJ IDEA.
  User: Raytine
  Date: 2019/4/18
  Time: 21:15
  To change this template use File | Settings | File Templates.

  @mark /user/admin/message  /user前缀是固定格式 不能缺少 /admin 用户登录id
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订阅指定用户的消息</title>
    <script type="text/javascript" src="../plugin/jquery3.4.0/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/sockjs.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/stomp.min.js"></script>
</head>
<script type="text/javascript">
    var noticeSocket=function () {
        var s=new SockJS("../wsuser");
        var stompClient=Stomp.over(s);
        stompClient.connect({},function () {
            console.log("notice socket connect");
            stompClient.subscribe("/user/admin/message",function(frame){
                console.log("订阅的消息");
                console.log(frame);
                $("#receive").html(frame.body);
            });
        });
    }
    noticeSocket();
</script>
<body>
<h1><span id="receive">等待接收消息</span></h1>
</body>
</html>
