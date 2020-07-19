<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "/public/common.ftl">
</head>
<body>
<div class="zdy_flex">
    <div style="min-width:650px">
        <blockquote class="layui-elem-quote zdy_bac-co-white layui-bg-gray" style="position: relative;">
            <button zdy-but="add" class="layui-btn layui-btn-sm"><i class="fa fa-remove fa-rotate-45 zdy_fa-rig5"></i>增加</button>
        </blockquote>
        <table id="role_table" class="layui-table" lay-filter="test"></table>
        <div class="layui-hide" id="but">
            <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
            <a class="layui-btn layui-btn-xs layui-bg-red" lay-event="del">删除</a>
        </div>
    </div>
    <div class="zdy_flex-auto zdy_mar-le5">
        <blockquote class="layui-elem-quote zdy_bac-co-white layui-bg-gray" style="position: relative;">
            <button zdy-but="addrole" id="addrole" class="layui-btn layui-btn-sm"><i class="fa fa-remove fa-rotate-45 zdy_fa-rig5"></i>保存</button>
        </blockquote>
        <input type="text" class="layui-hide" name="rid">
        <div class="layui-collapse">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">菜单管理</h2>
                <div class="layui-colla-content layui-show">
                    <div class="zdy_pad5">
                        <ul id="cd"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="md_radio" >
    <input type="radio" name="sex" title=" ">
</script>
<script type="text/javascript">
    layui.use(['element','table','form','tree','zdy'], function(){
        var element = layui.element,
                table = layui.table,
                form = layui.form,
                tree = layui.tree,
                zdy = layui.zdy,
                $ = layui.jquery,
                t = null;
        //表格加载
        table.render({
            elem: '#role_table'
            ,id:'role_table'
            ,url:'/system/usergroup/listUserGroupByPage'
            ,height: 'full-70'
            ,request: {
                pageName: 'pageNo' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , response: {
                statusCode: 200 //成功的状态码，默认：0
            }
            ,cols: [[ //标题栏
                {width:54,toolbar:'#md_radio',event:'md_radio',align:'right'}//自定义模板
                ,{field:'name',title:'角色名称', width:180}
                ,{field:'describe',title:'角色描述', width:200}
                ,{title:'操作', width:200,toolbar: '#but',fixed: 'right'}
            ]]
            ,skin: 'row' //表格风格
            ,even: true
            ,page: true //是否显示分页
            ,limit: 10 //每页默认显示的数量
            ,done: function(res,curr,count){//res-返回数据   curr-总条数   count-总页数
                treeadd(res.data[0].id);
                var tr = $(this.elem[0]).next("div").find("table tr"),
                        tr1 = tr.length > 0 ? tr[1] : false;
                if(tr1)$(tr1).find("td").eq(0).click().find("div").click();
            }
        });

        zdy.button('role_table',{
            add:{
                fun:function(ids,data){
                    parent.layui.layer.open({
                        type: 2
                        ,title:"新增角色"
                        ,skin: 'layui-layer-molv'//样式
                        ,content:"/system/usergroup/add"
                        ,area: ['500px', '350px']
                        , shadeClose: true
                        , anim: 1
                        , maxmin:true
                        , moveOut: true
                        , end: function () {
                            table.reload('role_table');//更新表格
                        }
                    });
                }
            },
            addrole:{
                fun:function(ids,data){
                    zdy.warn('您确定保存吗？', function () {
                        var rid = $('input[name="rid"]').val();
                        var auths = [];
                        $('input[name="author"]:checked').each(function () {
                            auths.push($(this).val());
                        });
                        var meuns = [];
                        $.each(t.getVal("datas"), function (k, v) {
                            meuns.push(v.id);
                        });
                        $.ajax({
                            url:"/system/quanxian/saveQuanxian"
                            , data: {
                                id: rid
                                , menuIds: meuns.join(",")
                            }
                            , context: document.body
                            , success: function (result) {
                                //result = $.parseJSON(result);
                                if (result.code =200) {
                                    layer.msg(result.msg, { icon: 1 });
                                } else {
                                    layer.msg(result.msg, { icon: 2 });
                                }
                            }
                        });
                    }, function () {
                        // console.log('取消');
                    });
                }
                //,must: true//是否 至少选择一行
            }

        });

        //操作按钮点击事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            switch(obj.event){
                case "md_radio":
                    treeadd(data.id);
                    break;
                case "edit":
                    layer.open({
                        type: 2
                        ,title:"修改角色"
                        ,skin: 'layui-layer-molv'//样式
                        ,content: "/system/usergroup/edit?id="+ data.id
                        ,area: ['500px', '350px']
                        ,shadeClose: true
                        ,anim: 1
                        ,moveOut: true
                        , end: function () {
                            table.reload('role_table');//更新表格
                        }
                    });
                    break;

                case "del":
                    zdy.warn("确认要删除记录？", function(){
                        zdy.remove("/system/usergroup/countUserByGroupid",{id:data.id},"role_table");
                    });
                    break;
            }
        });
        function treeadd(id){
            $("input[name='rid']").val(id);
            var div = "";
            //菜单树
            $.getJSON('/menu/getMenuTreeByUser?id='+id, function(data) {
                t = tree({
                    elem: '#cd', //ul 选择器
                    check: 'checkbox', //复选框（如果不需要则不写）
                    checkboxStyle: "",//设置复选框的样式，必须为字符串，css样式怎么写就怎么写
                    click: function (d) { //点击节点回调
                    },
                    onchange: function (d) {//复选框选择时回掉
                    },
                    nodes:data
                });
            });
        }
    });
</script>
</body>
</html>