<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/29
  Time: 15:20
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
    <link rel="stylesheet" href="static/layui/css/layui.css">
</head>
<body>
<script src="static/layui/layui.js"></script>
<div id="addStudentBox">
    <form class="layui-form" lay-filter="addform" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名"
                       autocomplete="off" class="layui-input" style="width:200px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="id" required lay-verify="required" placeholder="请输入ID" autocomplete="off"
                       class="layui-input" style="width:200px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text" class="layui-input" id="date" name="age" style="width:200px">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-form">
                <div class="layui-btn-group" style="margin-left: 100px">
                    <button class="layui-btn" lay-submit lay-filter="formDemo" id="addbtn">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary ">重置</button>
                    <button type="button" class="layui-btn layui-btn-danger" id="quit">取消</button>
                </div>
            </div>

        </div>
    </form>
</div>
<script src="static/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'laydate', 'table'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.$;
        $("#quit").click(function () {
            parent.closeopen();
        })
        //日期
        laydate.render({
            elem: '#date'
        });
        $("#addbtn").click(function () {
            var data = form.val("addform");
            $.ajax({
                url: 'student/add',
                data: data,
                success: function (result) {
                    table.reload('test'); //重载表格
                    if (result.code = 1) {
                        parent.closeopen();
                        layer.msg("添加成功", {icon: 1, time: 1000});
                        table.reload('test'); //重载表格
                    } else{
                        parent.closeopen();
                        layer.msg("添加失败", {icon: 2, time: 1000});
                    }
                }
            });
            parent.closeopen();

        });


    });
</script>
</body>
</html>
