<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加用户</title>
    <link rel="stylesheet" type="text/css" href="/public/layui/css/layui.css" /><!-- layui框架 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/extend.css" /><!-- 自定义扩展插件css -->
    <script type="text/javascript" src="/public/Jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/public/layui/layui.js"></script><!-- layui 框架 -->
    <script type="text/javascript" src="/public/layui/extend.js"></script><!-- 自定义扩展js类路径 -->

</head>
<body>
<form class="layui-form" lay-filter="userEditForm" style="margin: 30px 30px 30px 10px">
    <input type="hidden" name="orgcode" value="${orgCode}"/>
    <input type="hidden" name="imgId" id="imgId" />
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" name="realname" placeholder="请输入真实姓名" lay-verify="realname" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上传头像</label>
        <div class="layui-input-block">
            <img id="headImg" src="/public/images/public/zwtp.jpg" style="width: 200px;height: 150px"/>
            <button type="button" class="layui-btn" id="img" >
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" placeholder="请输入用户名" lay-verify="username" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" placeholder="请输入密码" lay-verify="password" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户组</label>
        <div class="layui-input-block">
            <select name="groupid" lay-filter="aihao">
                <#list ugList as ug>
                    <option value="${ug.id}">${ug.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
            <input type="text" name="phone" placeholder="请输入电话" lay-verify="required|phone" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电子邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" placeholder="请输入电子邮箱" lay-verify="required|email" autocomplete="off" class="layui-input">
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
    layui.use(['form','zdy','upload'], function(){
        var form = layui.form;
        var zdy = layui.zdy;
        var upload = layui.upload;

        //表单验证
        form.verify({
            realname:function (value,item) { //真实姓名
                if(!/^[\u4e00-\u9fa5]{2,4}$/.test(value)){
                    return "请输入2~4个汉字！";
                }
            },username:function (value,item) { //用户名
                if(!/^[a-zA-Z0-9]{5,12}$/.test(value)){
                    return "请输入5~12位字母或数字";
                 }
            },password:function (value,item) { //密码
                if(!/^[a-zA-Z0-9]{6,12}$/.test(value)){
                    return "请输入6~12位字母或数字";
                }
            }
        });

        //监听提交
        form.on('submit(*)', function (data) {
            zdy.form({
                url: "/system/user/userAdd"
                , data: data.field //当前容器的全部表单字段，名值对形式：{name: value}
            });

            return false;//阻止默认表单提交
        });

        //上传头像
        var uploadInst = upload.render({
            elem: '#img' //绑定元素
            ,url: '/system/user/upload' //上传接口
            ,done: function(res){
                if(res.code==200)
                $("#headImg").attr('src',"/system/user/imageShow?id="+res.data)
                $("#imgId").val(res.data);
            }
            ,error: function(){
                //请求异常回调
            }
        });



    });
</script>
</body>
</html>