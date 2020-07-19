<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "/public/common.ftl">
    <link rel="stylesheet" type="text/css" href="/public/layui/css/zdy/css/tableTree.css" />
    <script type="text/javascript" src="/public/layui/lay/zdy/tableTree.js"></script>
</head>
<body>
<div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
        <div class="code_tree">
            <ul class="code_tree-title">
                <li>
                    <div>序号</div>
                    <div>名称</div>
                    <div>组织机构代码</div>
                    <div>备注</div>
                    <div>操作</div>
                </li>
            </ul>
            <ul id="org-code" class="code_tree-centent"></ul>
        </div>
    </div>
</div>


<script>
    layui.use(['tableTree', 'layer', "element", "zdy"], function () {
        "use strict";
        var tableTree = layui.tableTree,
                layer = layui.layer,
                element = layui.element,
                zdy = layui.zdy;

        orgCode(tableTree, layer, zdy);//初始化调用

        element.on('tab(tab)', function (data) {
            var name = $(this).attr("tab");
            eval(name+"(tableTree,layer,zdy)");
        });
    });

    function orgCode(tableTree, layer, zdy){

        var tr = tableTree("#org-code","/system/xzqh/getXzqhTree");//加载
                //添加
                tr.addClick(function (data) {
                    open('/system/xzqh/add?upid='+data.id);
                }),

                //修改
                tr.editClick(function (data) {
                    open('/system/xzqh/edit?id=' + data.id);
                }),

                //删除po
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