<%--
  Created by IntelliJ IDEA.
  User: Raytine
  Date: 2019/4/14
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebSocket测试页面</title>
    <script type="text/javascript" src="../plugin/jquery3.4.0/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="../plugin/websocket/websocket.js"></script>
</head>
<body>
<span>测试一下WebSocket站点吧</span>
<input id="message" type="text">
<button onclick="sendMessage()">发送消息</button>
<button onclick="closeWebsocket()">关闭WebSocket连接</button>
<div id="context"></div>
</body>
</html>
