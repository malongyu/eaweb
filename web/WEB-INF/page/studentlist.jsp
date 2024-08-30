<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/26
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
学生信息表
${sessionScope.studentlist}
<table>
    thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
        <th>sex</th>
    </tr>
    </thead>
    <c:forEach items="${studentlist}" var="student">
        <tr>
        <td>${student.sid}</td>
        <td>${student.sname}</td>
        <td>${student.sage}</td>
        <td>${student.ssex}</td>
        </tr>
        </c:forEach>
</table>
</body>
</html>
