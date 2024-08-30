<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/26
  Time: 15:46
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
省份<select name="province" _changedomname="city">

</select>
城市<select name="city" _changedomname="area">

</select>
地区<select name="area">

</select>

<table id="datalist">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>age</td>
        <td>sex</td>
    </tr>
</table>
<h1>通过ajax访问数据并显示</h1>
<script src="static/js/jquery-3.6.3.js"></script>
<script>
    //在该页面发送ajax请求 获取student数据
    //解析该数据 并且显示在页面上
    $.ajax({
        url: "student/ajaxlist",
        data: {},
        success: function (result) {
            console.log(result);
            result = JSON.parse(result);
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                var text = "<tr><td>" + item.sid + "</td><td>" + item.sname + "</td><td>" + item.sage + "</td><td>" + item.ssex + "</td></tr>";
                $("#datalist").append(text);
            }
        }
    });

    function showitem($dom, a) {
        $dom.html('');
        for (var item of a) {
            var text = "<option value='" + item.id + "'>" + item.name + "</option>";
            $dom.append($(text));
        }
    }

    //showprovince();
    function showprovince() {
        $.ajax({
            url: "area/list",
            data: {parent_id: 0},
            success: function (result) {
                result = JSON.parse(result);
                showitem($("[name='province']"), result)

            }

        })
    }

    showArea(0, "province")

    function showArea(parent_id, domName) {
        $.ajax({
            url: "area/list",
            data: {parent: parent_id},
            success: function (result) {
                result = JSON.parse(result);
                showitem($("[name='" + domName + "']"), result)

            }

        });
    }

    $("[name='province'],[name='city']").change(function () {
        //获取当前组建的内容
        var item = $(this).val();
        //根据当前组建的value值 去查询子级信息
        showArea(item, $(this).attr("_changedomname"));
        //将查询的数据显示在组件上
    });
</script>
</body>
</html>
