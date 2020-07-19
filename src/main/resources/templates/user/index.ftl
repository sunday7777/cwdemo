<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/public/layui/css/layui.css" /><!-- layui框架 -->
    <link rel="stylesheet" type="text/css" href="/public/fontawesome/css/font-awesome.css" /><!-- 字体 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/tool.css" /><!-- 自定义公共类 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/zdy-table.css" /><!-- table 必须加载 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/default.css" /><!-- 默认css修改 -->
    <link rel="stylesheet" type="text/css" href="/public/zdy/css/extend.css" /><!-- 自定义扩展插件css -->
    <link rel="stylesheet" type="text/css" href="/public/layui/css/zdy/css/tableTree.css" />
    <script type="text/javascript" src="/public/Jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/public/layui/layui.js"></script><!-- layui 框架 -->
    <script type="text/javascript" src="/public/layui/extend.js"></script><!-- 自定义扩展js类路径 -->
    <script type="text/javascript" src="/public/layui/lay/zdy/tableTree.js"></script>




</head>
<body>
<div class="zdy_flex">
    <div style="min-width:230px;">
        <div class="layui-collapse zdy-nav">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">组织机构</h2>
                <div class="layui-colla-content layui-show zdy_pad5">
                    <ul id="code"></ul>
                </div>
            </div>
        </div>
    </div>
    <div class="zdy_flex-auto zdy_mar-le5">
        <blockquote class="layui-elem-quote zdy_bac-co-white layui-bg-gray">
            <button zdy-but="add" class="layui-btn layui-btn-sm"><i class="fa fa-remove fa-rotate-45 zdy_fa-rig5"></i>增加</button>
            <button zdy-but="edit" class="layui-btn layui-btn-sm"><i class="fa fa-pencil zdy_fa-rig5"></i>修改</button>
            <button zdy-but="remove" class="layui-btn layui-btn-sm layui-btn-danger"><i class="fa fa-close zdy_fa-rig5"></i>删除</button>
        </blockquote>
        <table id="user-table" class="layui-table" lay-filter="test"></table>
    </div>
</div>
<script>
    $(function () {
    layui.use(['table', 'zdy', 'layer', 'tree'], function(){
        var table = layui.table;
        var zdy   = layui.zdy;
        var layer = layui.layer;
        var tree = layui.tree;

        //数据表格
        table.render({
            id:"user"
            ,elem: '#user-table'
            ,height: 512
            ,url: '/system/user/findUserByPage' //数据接口
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
                { checkbox: true }
                ,{field: 'realname', title: '操作', width:120}
                ,{field: 'username', title: '编码', width:120}
                ,{field: 'groupid', title: '名称', width:120}
                ,{field: 'phone', title: '科目性质', width: 180}
                ,{field: 'email', title: '邮箱', width: 180}
                ,{field: 'orgcode', title: '行政区划', width: 120}
                ,{field: 'lastlogintime', title: '上次登陆时间', width: 120}
                ,{field: 'nums', title: '登陆次数', width: 120}
            ]]
        });

        //行政区划树
        $.getJSON( '/system/xzqh/getXzqhTree', function (data) {
            tr = tree({
                elem: '#code', //ul 选择器
                nodes: data,
                click: function (d) { //点击节点回调
                    table.reload('user', {
                        where: {
                            orgCode: d.attributes
                        }
                    })
                }
            });
        });

        //工具栏
        zdy.button('user',{
            add:{
                fun:function(ids,data){
                    var val = tr.getVal("data");
                    if(!val){
                        zdy.hint('请选择一个组织机构');
                        return;
                    }
                    parent.layui.layer.open({
                        type: 2
                        , title: "添加用户"
                        , skin: 'layui-layer-molv'//样式
                        , content:'/system/user/add?orgCode='+val.attributes
                        , area: ['530px', '500px']
                        , shadeClose: true
                        , anim: 1
                        , maxmin:true
                        , moveOut: true
                        , end: function () {
                            table.reload('user');//更新表格
                        }
                    });
                }
            },
            edit:{
                fun:function(id,data){
                    parent.layui.layer.open({
                        type: 2
                        , title: "修改用户"
                        , skin: 'layui-layer-molv'//样式
                        , content:'/system/user/edit?id='+id
                        , area: ['530px', '500px']
                        , shadeClose: true
                        , anim: 1
                        , maxmin:true
                        , moveOut: true
                        , end: function () {
                            table.reload('user');//更新表格
                        }
                    });
                }
            },
            remove:{
                must:true,
                fun:function(id,data){
                    if(data[0].username=="admin"){
                        zdy.hint("此用户为超级管理员不能删除");
                        return;
                    }
                    zdy.warn('您确定要删除该条信息吗？', function() {
                        zdy.remove('/system/user/userDel', { id: id }, 'user');
                    });
                }

            }
        });

    });
    });
</script>
</body>
</html>