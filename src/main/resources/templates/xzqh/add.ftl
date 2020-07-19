<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "/public/common.ftl">
    <link rel="stylesheet" type="text/css" href="/public/layui/css/zdy/css/tableTree.css" />
    <script type="text/javascript" src="/public/layui/lay/zdy/tableTree.js"></script>
</head>
<body class="layui-layout-body zdy-body">
<form class="layui-form layui-form-pane">
    <input type="hidden" name="upid" value="${upid}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required|title" placeholder="请输入名称" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">本级代码：</label>
        <div class="layui-input-block">
            <input type="text" name="incode" placeholder="请输入代码" class="layui-input" lay-verify="required|incode">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block">
            <input type="text" name="remark" placeholder="请输入备注" class="layui-input">
        </div>
    </div>
    <div class="form_zdy-but">
        <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>


<script>
    layui.use(['form', 'layedit', 'zdy'], function () {
        var form = layui.form,
                layedit = layui.layedit,
                zdy = layui.zdy;

        //表单验证
        form.verify({
            incode:function (value, item) {
                if(!/^\d{2,3}$/.test(value)){
                    return "请输入俩到三位数字";
                }
            }
        });

        //监听提交
        form.on('submit(*)', function (data) {
            zdy.form({
                url: "/system/xzqh/xzqhAdd",
                data: data.field //当前容器的全部表单字段，名值对形式：{name: value}
            });
            return false;//阻止默认表单提交
        });


    });
</script>
</body>
</html>