<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/26
  Time: 9:13
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
在线人数是<%=application.getAttribute("ON_LINE_NUM")%>
</body>
</html>
