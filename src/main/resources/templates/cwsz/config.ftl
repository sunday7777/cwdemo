<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "/public/common.ftl">
</head>
<body>
<div class="zdy_flex-auto zdy_mar-le5">
    <form  class="layui-form" id="setConfig">
        <input type="hidden" name="id" value="${config.id}"/>
    <table border="1"  width="50%" align="center" style="margin-top: 100px;color: #777">
        <caption style="text-align: left;padding: 10px;color: #777">系统参数选项设置</caption>
        <tr style="height: 50px">
            <td style="text-align: right;width: 20%">账套名称：</td>
            <td>&nbsp;&nbsp;&nbsp;<input type="text" name="ztmc" value="${config.ztmc}" lay-verify="required" style="width: 70%;border: none"/></td>
        </tr>
        <tr style="height: 50px">
            <td style="text-align: right">启用日期：</td>
            <td>&nbsp;&nbsp;&nbsp;<input type="text" name="qyrq" value="${config.qyrq}" lay-verify="required" class="layui-input" id="qyrq" style="border: none;"></td>
        </tr>
        <tr style="height: 50px">
            <td style="text-align: right">当前月份：</td>
            <td>&nbsp;&nbsp;&nbsp;<input type="text" name="dqny" value="${config.dqny}" lay-verify="required" style="width: 70%;border: none"/></td>
        </tr>
        <tr style="height: 50px">
            <td style="text-align: right">科目级次：</td>
            <td>&nbsp;&nbsp;&nbsp;<input type="text" name="kmjc" value="${config.kmjc}" lay-verify="required" style="width: 70%;border: none"/></td>
        </tr>
        <tr style="height: 50px">
            <td style="text-align: right">科目级长：</td>
            <td>&nbsp;&nbsp;&nbsp;<input type="text" name="kmcd" value="${config.kmcd}" lay-verify="required" style="width: 70%;border: none"/></td>
        </tr>
        <tr style="height: 50px">
            <td colspan="2" style="text-align: center">
                <button class="layui-btn" name="" lay-submit lay-filter="*">保存</button>
            </td>
        </tr>
    </table>
    </form>
</div>
<script>
    $(function () {
        layui.use(['zdy', 'layer', 'laydate'], function(){
            var zdy   = layui.zdy;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var form = layui.form;

            //监听提交
            form.on('submit(*)', function (data) {
                zdy.form({
                    url: "/cw/config/setConfig"
                    , data: data.field //当前容器的全部表单字段，名值对形式：{name: value}
                });

                return false;//阻止默认表单提交
            });

            //执行一个laydate实例
            laydate.render({
                elem: '#qyrq' //指定元素
            });

        });
    });
</script>
</body>
</html>