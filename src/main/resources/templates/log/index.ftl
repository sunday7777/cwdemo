<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "/public/common.ftl">
</head>
<body>
<div class="zdy_flex-auto zdy_mar-le5">
    <blockquote class="layui-elem-quote zdy_bac-co-white layui-bg-gray">
        <a href="/system/log/excelExport" ><button zdy-but="export" class="layui-btn layui-btn-sm"><i class="fa fa-remove fa-rotate-45 zdy_fa-rig5"></i>导出</button></a>
    </blockquote>
    <table id="log-table" class="layui-table" lay-filter="test"></table>
</div>
<script>
    $(function () {
    layui.use(['table', 'zdy', 'layer'], function(){
        var table = layui.table;
        var zdy   = layui.zdy;
        var layer = layui.layer;

        //数据表格
        table.render({
            id:"log"
            ,elem: '#log-table'
            ,height: 512
            ,url: '/system/log/findLogByPage' //数据接口
            ,request: {
                pageName: 'pageNo' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , response: {
                 statusCode: 200 //成功的状态码，默认：0
            }
            ,page: true //开启分页
            ,even: true //隔行变色
            ,cols: [[ //表头
                {field: 'cztype', title: '操作类型', width:120}
                ,{field: 'czzh', title: '操作账号', width:120}
                ,{field: 'cztime', title: '操作时间', width:200}
                ,{field: 'content', title: '操作内容', width: 300}
                ,{field: 'ip', title: '操作ip', width: 180}
            ]]
        });

    });
    });
</script>
</body>
</html>