<!DOCTYPE html>
<html>

<head>
    <title>cwDemo</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="shortcut icon" href="/public/images/home/cw.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="/public/login/css/css.css" />
    <link rel="stylesheet" type="text/css" href="/public/login/css/reset.css" />
    <script type="text/javascript" src="/public/Jquery/jquery-3.2.1.min.js"></script>

</head>
<body>
<div class="login_main">
    <img src="/public/images/login/bg.jpg" width="100%" height="100%"/>
    <div class="top1">cwDemo</div>
    <div class="login_box">
        <div class="dlxx">
            <form id="loginForm">
                <input id="username" name="username" type="text" class="" placeholder="用户名"/>
                <input id="password" name="password" type="password" class="password" placeholder="登录密码"/>
                <input type="text" class="yzm" placeholder="请输入右侧验证码" id="validatecode" name="validatecode" />
                <div class="yz">
                    <img width="100%" height="100%" id="validImg" alt="点击换一个验证码" title="点击换一个验证码" src="/login/captcha.jpg" onclick="this.src='/login/captcha.jpg?'+Math.random()" />
                </div>
                <div style="clear:both"></div>
                <div><font class="error"></font></div>
                <div class="submit-btn">
                    <input type="button" onclick="return validateForm();" value="登录" />
                </div>
            </form>
        </div>
    </div>
    <div class="bot">版权所有：我是个馒头 &nbsp;&nbsp;&nbsp;&nbsp;邮箱：864944649@qq.com</div>
</div>

<script type="text/javascript">

    function validateForm() {
        var name = $("#username").val();
        var password = $("#password").val();
        var validatecode = $("#validatecode").val();

        if (name == "") {
            $(".error").text('请输入用户名！');
            name.focus();
            return false;
        }else if (password == "") {
            $(".error").text('请输入密码！');
            password.focus();
            return false;
        }else if(validatecode == ""){
            $(".error").text('请输入右侧验证码！');
            validatecode.focus();
            return false;
        }else{
            $(".error").text('');
            $.post("/system/user/userLogin",{username:name,password:password,validatecode:validatecode},function (data) {
                if(data.code == 200){
                    $(".error").text('');
                    window.location.href="/";
                }else{
                    $(".error").text(data.msg);
                }
                console.log(data)
            });
        }

    }
</script>
<div class="clear"></div>
</body>

</html>
