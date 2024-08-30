<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
登陆页面
<form action="login" method="get">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="userpass"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
