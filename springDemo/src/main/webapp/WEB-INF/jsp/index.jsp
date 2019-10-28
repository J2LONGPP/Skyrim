<%--
  Created by IntelliJ IDEA.
  User: Raytine
  Date: 2018/12/13
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<span>欢迎进入首页</span>
<body>
<%--文件上传功能测试--%>
<form method="post" action="file/upload/request" enctype="multipart/form-data">
    <input type="file" name="file" value="请选择上传的文件">
    <input type="submit" value="提交">
</form>
</body>
</html>
