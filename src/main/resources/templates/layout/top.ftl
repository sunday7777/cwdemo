<!DOCTYPE html>
<html lang="en">
<head>
    <title>cstest</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/public/layui/css/layui.css" /><!-- layui框架 -->
    <link rel="stylesheet" type="text/css" href="/public/fontawesome/css/font-awesome.css" /><!-- 字体 -->
    <link rel="stylesheet" href="/public/fontawesome/iconfont/iconfont.css" /><!-- 字体图标 阿里图标 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/tool.css" /><!-- 自定义公共类 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/zdy-table.css" /><!-- table 必须加载 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/default.css" /><!-- 默认css修改 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/extend.css" /><!-- 自定义扩展插件css -->
    <script type="text/javascript" src="/public/Jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/public/layui/layui.js"></script><!-- layui 框架 -->
    <script type="text/javascript" src="/public/layui/extend.js"></script><!-- 自定义扩展js类路径 -->
    <script src="/public/echarts/echarts.min.js"></script>
    <link rel="shortcut icon" href="/public/images/home/cw.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/public/zdy/css/zdy-main.css"><!-- 主页自定义文件-->
    <script src="/public/mCustomScrollbar/js/jquery.mCustomScrollbar.min.js"></script><!-- 滚动条插件 -->
    <script src="/public/mCustomScrollbar/js/jquery.mousewheel.min.js"></script><!-- 滚动条插件 -->
    <script src="/public/main/js/main.js"></script><!-- 主要 -->
    <script src="/public/main/js/nav_main.js"></script><!-- tab 页头方法 -->
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!--********** 头部 Start **********-->
    <div class="layui-header">
        <div class="layui-logo">cstest</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item zdy-nav-remove">
                <a href="javascript:;"><i class="fa fa-envelope-o fa-fw fa-r-5"></i>消息<span id="badge" class="layui-badge"></span></a>
                <ul id="hintID" class="layui-nav-child zdy-nav-alter zdy-nav-arrows">
                    <li tab="details-tab1">
                        <i class="fa fa-user fa-border fa-fw ts1"></i>
                        <span>人口提醒</span>
                        <span class="layui-badge" id="man_id_span"></span>
                    </li>
                    <li tab="details-tab2">
                        <i class="fa fa-automobile fa-border fa-fw ts2"></i>
                        <span>车辆提醒</span>
                        <span class="layui-badge" id="vehicle_id_span"></span>
                    </li>
                    <li tab="details-tab3">
                        <i class="fa fa-yen fa-border fa-fw ts3"></i>
                        <span>资产提醒</span>
                        <span class="layui-badge" id="asset_id_span"></span>
                    </li>
                    <li tab="details-tab4">
                        <i class="fa fa-file-text-o fa-border fa-fw ts4"></i>
                        <span>合同提醒</span>
                        <span class="layui-badge" id="pact_id_span"></span>
                    </li>
                    <li tab="details-tab5">
                        <i class="fa fa-skyatlas fa-border fa-fw ts5"></i>
                        <span>其他提醒</span>
                        <span class="layui-badge" id="calendar_id_span"></span>
                    </li>
                    <li tab="details-tab6">
                        <i class="fa fa-group fa-border fa-fw ts6"></i>
                        <span>会议培训</span>
                        <span class="layui-badge" id="meeting_id_span"></span>
                    </li>
                </ul>
            </li>
            <li class="layui-nav-item">
                <a id='aaa' href="javascript:;">
                    <img src="/system/user/getUserImg?userid=${id}" class="layui-nav-img">
                    欢迎，<span id="user-name">${realname}</span>
                </a>
                <dl class="layui-nav-child zdy-nav-arrows">
                    <dd><a id="basicsID" href="javascript:;"><i class="fa fa-user-circle-o fa-r-5"></i>基本信息</a></dd>
                    <dd><a id="updateID" href="javascript:;"><i class="fa fa-eye fa-r-5"></i>密码修改</a></dd>
                    <dd><a id="settingID" href="javascript:;"><i class="fa fa-gear fa-r-5"></i>系统设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a  href="/system/user/logout"><i class="fa fa-power-off fa-fw">&nbsp;&nbsp;</i>注销</a>
            </li>
        </ul>
    </div>
    <!--********** 头部 End **********-->