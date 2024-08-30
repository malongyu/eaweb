<%@ page import="com.easy.util.Sys" %><%--
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
登陆成功
<%=session.getAttribute(Sys.LOGIN_USER)%>
<a href="staff/list">退出</a>
<a href="jsp/ajaxstudent">退出</a>
</body>
</html>
