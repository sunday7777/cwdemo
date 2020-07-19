<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增角色</title>
    <#include "/public/common.ftl">

</head>
<body>
<form class="layui-form" lay-filter="userEditForm" style="padding: 30px 30px 30px 10px">
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" placeholder="角色名称" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色描述</label>
        <div class="layui-input-block">
            <input type="text" name="describe" placeholder="角色描述" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
</form>
<script>
    layui.use(['form','zdy'], function(){
        var form = layui.form;
        var zdy = layui.zdy;

        //监听提交
        form.on('submit(*)', function (data) {
            zdy.form({
                url: "/system/usergroup/saveUserGroup"
                , data: data.field //当前容器的全部表单字段，名值对形式：{name: value}
            });

            return false;//阻止默认表单提交
        });



    });
</script>
</body>
</html>