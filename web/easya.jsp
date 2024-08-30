<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/18
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<!--HTML 注释-->
<%-- JSP 注释 --%>
<%
    //java代码脚本
    //局部变量
    int a = 11;
    if (a % 2 == 0) {
%>
<div style="background-color: red;height: 100px"></div>
<%
} else {%>
<div style="background-color: #f7ff00;height: 100px"></div>
<%
    }
%>
<h1><%=a%></h1>
<%@ include file="index.jsp" %>
<%--打印脚本--%>
<%--声明脚本--%>
<%!
//全局变量
    int a=12;
    List<String> list = new ArrayList<>();
%>
</body>
</html>
