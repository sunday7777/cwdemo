<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "/public/common.ftl">
    <link rel="stylesheet" type="text/css" href="/public/layui/css/zdy/css/tableTree.css" />
    <script type="text/javascript" src="/public/layui/lay/zdy/kmzdTree.js"></script>
</head>
<body>
<div class="zdy_flex-auto">
    <blockquote class="layui-elem-quote zdy_bac-co-white layui-bg-gray">
        科目类别：
        <div class="layui-btn-group">
            <button type="button" class="layui-btn">资产</button>
            <button type="button" class="layui-btn">负债</button>
            <button type="button" class="layui-btn">权益</button>
            <button type="button" class="layui-btn">成本</button>
            <button type="button" class="layui-btn">损益</button>
        </div>
    </blockquote>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="code_tree">
                <ul class="code_tree-title">
                    <li>
                        <div>序号</div>
                        <div>名称</div>
                        <div>编码</div>
                        <div>科目性质</div>
                        <div>操作</div>
                    </li>
                </ul>
                <ul id="org-code" class="code_tree-centent"></ul>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        layui.use(['kmzdTree', 'layer', 'laydate'], function(){
            var zdy   = layui.zdy;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var kmzdTree = layui.kmzdTree;

            orgCode(kmzdTree, layer, zdy);//初始化调用

            element.on('tab(tab)', function (data) {
                var name = $(this).attr("tab");
                eval(name+"(kmzdTree,layer,zdy)");
            });
        });
    });

    function orgCode(kmzdTree, layer, zdy){

        var tr = kmzdTree("#org-code","/cw/kmzd/listKmzdTree?type=1");//加载
        //添加
        tr.addClick(function (data) {
            open('/system/xzqh/add?upid='+data.id);
        }),

            //修改
            tr.editClick(function (data) {
                open('/system/xzqh/edit?id=' + data.id);
            }),

            //删除
            tr.removeClick(function (data) {
                //询问框
                zdy.warn("该操作会同时删除所有下级行政区划！<br>您确定要删除吗？", function () {
                    $.getJSON("/system/xzqh/xzqhDel", { orgcode: data.pid }, function (d) {
                        console.log(d);
                        if (d.code==200) {
                            layer.msg(d.msg, { icon: 1, time: 2000 });
                            tr.reload();//更新
                        } else {
                            layer.msg(d.msg, { icon: 5, time: 2000 });
                        }
                    });
                });
            });

        function open(url) {
            parent.layui.layer.open({
                type: 2
                , title: "增加"
                , skin: 'layui-layer-molv'//样式
                , content: url
                , area: ['500px', '400px']
                , shadeClose: true
                , anim: 1
                , moveOut: true
                , yes: function () {
                    // console.log("确认按钮");
                }
                , cancel: function () {
                    // console.log("关闭按钮");
                }
                , end: function () {
                    // console.log("销毁");
                    tr.reload();//更新
                }
            });
        }
    }
</script>
</body>
</html>