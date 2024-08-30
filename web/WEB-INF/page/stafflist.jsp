<%@ page import="com.easy.bean.Staff" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/26
  Time: 9:48
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
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>height</th>
        <th>age</th>
        <th>tel</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <%List<Staff> list = (List<Staff>) request.getAttribute("stafflist");%>
    <%for (Staff staff : list) {%>
    <tr>
        <td><%=staff.getId()%></td>
        <td><%=staff.getName()%></td>
        <td><%=staff.getHeight()%></td>
        <td><%=staff.getAge()%></td>
        <td><%=staff.getTel()%></td>
    </tr>
    <%}%>
    </tbody>
</table>

</body>
</html>
