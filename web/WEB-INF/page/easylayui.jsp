<%--
  Created by IntelliJ IDEA.
  User: malon
  Date: 2024/8/26
  Time: 20:41
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
    <%-- 表格中的行内按钮--%>
    <link rel="stylesheet" href="static/layui/css/layui.css">

</head>
<body>

<table class="layui-table" lay-data="{ url:'student/layuilist',toolbar:'#toptools', page:true, id:'test'}"
       lay-filter="test">
    <thead>
    <tr>
        <th lay-data="{type:'numbers'}">序号</th>
        <th lay-data="{field:'sid'}">ID</th>
        <th lay-data="{field:'sname'}">用户名</th>
        <th lay-data="{field:'ssex'}">性别</th>
        <th lay-data="{field:'sage'}">年龄</th>
        <th lay-data="{toolbar:'#barDemo',align:'center'}">删除</th>
    </tr>
    </thead>
</table>
<script type="text/html" id="toptools">
    <form class="layui-form-item" id="topbox">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">ID</label>
            <div class="layui-input-inline">
                <input type="text" name="id" placeholder="请输入ID" autocomplete="off" class="layui-input">
            </div>
            <a class="layui-btn layui-btn-primary  layui-bg-red" lay-event="query">查询</a>
            <a class="layui-btn layui-btn-primary  layui-bg-red" lay-event="add">新增</a>
            <a class="layui-btn layui-btn-primary  layui-bg-red" lay-event="newadd">新增</a>

        </div>
    </form>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs layui-bg-red" lay-event="detail">删除</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs layui-bg-red" lay-event="edit">编辑</a>

</script>
<script src="static/layui/layui.js"></script>

<%--将div嵌入表头 使用display隐藏 让其不能显示在页面上--%>
<div id="addStudentBox" style="display: none">
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
                <input type="text" class="layui-input" id="date" name="age">
            </div>
        </div>
    </form>
</div>
<div id="editStudentBox" style="display: none">
    <form class="layui-form" lay-filter="editform" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="sname" required lay-verify="required" placeholder="请输入姓名"
                       autocomplete="off" class="layui-input" style="width:200px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">ID</label>
            <div style="display: flex; align-items: baseline;">
                <input type="text" name="sid" readonly required class="layui-input" style="width:200px;">
                <span style="margin-left: 5px;opacity: 0.4;">禁止修改</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="ssex" value="男" title="男">
                <input type="radio" name="ssex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text" class="layui-input" id="date1" name="sage">
            </div>
        </div>
    </form>
</div>
<script>
    var closeopen;
    layui.use(['table', 'form', 'laydate'], function () {
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.$;
        laydate.render({//渲染时间选择器
            elem: '#date,#date1', //指定元素id
            type: 'datetime'
        });
        closeopen=function(){
            layer.closeAll();
        }
        /*表头头部按钮工具栏事件*/
        table.on("toolbar(test)", function (obj) {
            var layEvent = obj.event;
            if (layEvent == 'newadd') {
                layer.open({
                    type: 2,
                    title: '新增学生',
                    area: ['450px', '350px'],//大小
                    content:"jsp/addStudent",
                });
            } else if (layEvent == 'query') {
                //点击了查询按钮
                //获取搜索框的值
                var name = $('#topbox [name="name"]').val();
                var id = $('#topbox [name="id"]').val();
                table.reload('test', {
                    where: {sname: name, sid: id,},
                    page: {curr: 1} //重新从第一页开始
                });
                $('#topbox [name="name"]').val(name);
                $('#topbox [name="id"]').val(id);
            } else if (layEvent == 'add') {
                //表单重置
                form.val("addform", {
                    "name": "",
                    "id": "",
                    "sex": "男",
                    "age": ""
                });
                //点击新增按钮
                //弹出层 显示表单
                layer.open({
                    type: 1,
                    title: '新增学生',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['350px', '330px'],//大小
                    content: $('#addStudentBox'),
                    closeBtn: 1,
                    btn: ['确定', '取消', '重置'],
                    btn1: function () {
                        //layui可以直接获取表单的数据 只要组件有class="layui-form"样式
                        var data = form.val("addform");
                        $.ajax({
                            url: 'student/add',
                            data: data,
                            success: function (result) {
                                table.reload('test', {
                                    where: {sname: name, sid: id,},
                                    page: {curr: 1} //重新从第一页开始
                                });
                                table.reload('test'); //重载表格
                                if (result.code = 1) {
                                    layer.msg("添加成功", {icon: 1, time: 1000});
                                    table.reload('test'); //重载表格
                                } else
                                    layer.msg("添加失败", {icon: 2, time: 1000});
                            }
                        });
                        console.log(data);
                        layer.closeAll(); //关闭弹出层
                    },
                    btn3: function () {
                        //重置 将数据重新赋值
                        form.val("addform", {
                            "name": "",
                            "id": "",
                            "sex": "男",
                            "age": ""
                        });
                    },
                    end: function () {
                        table.reload('test'); //重载表格
                    }
                });
            }
        });

        /*表格行内按钮事件*/
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url: 'student/delete',
                        data: data,
                        success: function (result) {
                            table.reload('test'); //重载表格
                            console.log(result.code);
                            if (result.code = 1) {
                                layer.msg("删除成功", {icon: 1, time: 1000});
                                table.reload('test'); //重载表格
                            } else
                                layer.msg("删除失败", {icon: 2, time: 1000});
                        }
                    });
                });
            } else if (layEvent == 'edit') {
                ////将当前行数据设置到表单中
                form.val("editform", data);
                //弹出层 显示表单
                layer.open({
                    type: 1,
                    title: '修改信息',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['450px', '330px'],//大小
                    content: $('#editStudentBox'),
                    closeBtn: 1,
                    btn: ['保存', '取消'],
                    btn1: function () {
                        var data = form.val("editform");
                        $.ajax({
                            url: 'student/edit',
                            data: data,
                            success: function (result) {
                                table.reload('test'); //重载表格
                                if (result.code = 1) {
                                    layer.msg("修改成功", {icon: 1, time: 1000});
                                    table.reload('test'); //重载表格
                                } else
                                    layer.msg("修改失败", {icon: 2, time: 1000});
                            }
                        });
                        layer.closeAll(); //关闭弹出层
                    }
                });
            }
        });
    });
</script>
<script>


</script>
</body>
</html>
</body>
</html>
