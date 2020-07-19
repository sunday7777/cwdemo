layui.define(['layer', 'table', 'form', 'element'], function (exports) {
    "use strict";
    var layer = layui.layer,
        table = layui.table,
        form = layui.form,
        element = layui.element;

    var d = {},
        A = function (e) {

        };

    //按钮事件（新）
    d.but = function (id, parameters, fun) {
        var but = parameters.butid,//按钮ID
            row = parameters.row,//是否允许多行操作（默认：false不允许）
            must = parameters.must,//判断是否至少选择一行
            zdy = parameters.zdy; //自定义验证

        id || fun && fun();

        var checkStatus = table.checkStatus(id).data;//table 已选中数据

        // $("[zdy-but='" + key + "']").on("click", function () {
        //     row && checkStatus
        // });
    },
    // 按钮组点击事件
    d.button = function (id, parameters) {
        // 说明：
        //     参数：id: table 数据表格id 
        //           parameters: Object {key:data,key1:data1}
        //           key: 对应标签 zdy-but="key"
        //           data: 参数
        //                 row: Boole     是否允许多行操作（默认：false不允许）
        //                 must: Boole    是否判断至少选择一行
        //                 zdy: Array    自定义验证
        //                          例：[{name:"sort",value:1,news:"对的"}]
        //                                  name:验证字段名称
        //                                  value:字段值（如果等于则不通过）
        //                                  news:提示文字
        //
        //                 fun: function(ids,data)  回掉函数
        //                          ids: String  已选中行id（如果是多行则id以“,”隔开）
        //                          data: Array  已选中数据
        $.each(parameters, function (key, val) {
            $("[zdy-but='" + key + "']").off().on("click", function () {
                var checkStatus = table.checkStatus(id);
                if (!val.row) {
                    if (checkStatus.data.length > 1) {
                        d.hint('不允许多行操作！');
                        return false;
                    }
                }
                if (!!val.must) {
                    if (checkStatus.data.length < 1) {
                        d.hint('至少选择一行数据！');
                        return false;
                    }
                }
                if (!!val.zdy) {
                    var pd = true;
                    $.each(val.zdy, function (k, v) {
                        $.each(checkStatus.data, function (k1, v1) {
                            if (v1[v.name] == v.value) {
                                pd = false;
                                return pd;
                            }
                        });
                        if (!pd) d.hint(v.news);
                        return pd;
                    });
                    if (!pd) return false;
                }
                var ids = "";
                for (var i = 0, len = checkStatus.data.length; i < len; i++) {
                    ids += checkStatus.data[i].id
                    if(i!=len-1)ids += ",";
                }
                val.fun && val.fun(ids, checkStatus.data);
            });
        });
    },
    // 下拉框加载数据
    d.select = function (parameters) {
        // 说明：
        //     参数: parameters: 参数
        //                 elem: String   select 标签 选择器 （例如：#id）
        //                 url: String    数据查询链接
        //                 data: Object   参数
        //                 cols: Object   返回值对应的 select标签内的值
        //                       例如： cols: {
        //                                  value: "id",
        //                                  text: "text"
        //                              }
        $.getJSON(parameters.url, parameters.data, function (data) {
            var jq = $(parameters.elem),
                nv = "<option></option>";
            var value = $.trim(jq.attr("value")+""),
                sel = "";
            jq.empty();
            $.each(data, function (key, val) {
            	var s = val[parameters.cols.date] ? val[parameters.cols.date] +'年':'';
                sel = $.trim(val[parameters.cols.value]+"") == value ? "selected" : "";
                nv += "<option value='" + val[parameters.cols.value] + "' " + sel + ">" + s +val[parameters.cols.text] + "</option>"
            });
            jq.append(nv);

            form.render("select");
            return nv;
        });
    },
    // form表单提交
    d.form = function (parameters) {
        // 说明：
        //     参数: parameters: 参数
        //                 url: String    数据查询链接
        //                 data: Object   传参 
        //                 fun: function  回掉函数
        //                 id: String     数据表格内id
        layer.load();
        $.ajax({
            url: parameters.url
            , data: JSON.stringify(parameters.data)
            , dataType: "json"
            , contentType : 'application/json'
            , error: function (jqXHR, textStatus, errorThrown) {
                layer.closeAll('loading');
                layer.alert('错误！请联系管理员。', { icon: 7 });
            }
            , type: "POST"
            , success: function (data) {
                layer.closeAll('loading');
                if (data.code=200) {
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                    parent.layer.msg(data.msg, { icon: 1, anim: 5, shadeClose: true, time: 600, shade: [0.1] });
                } else {
                    parent.layer.msg(data.msg, { icon: 2, anim: 6, shadeClose: true, time: 1000, shade: [0.1] });
                };
                parameters.fun && parameters.fun(data);
            }
        });
    },
    
    // form表单提交(非弹窗提交表单)
    d.formTem = function (parameters) {
        // 说明：
        //     参数: parameters: 参数
        //                 url: String    数据查询链接
        //                 data: Object   传参 
        //                 fun: function  回掉函数
        //                 id: String     数据表格内id
        layer.load();
        $.ajax({
            url: parameters.url
            , data: parameters.data
            , dataType: "json"
            , contentType : 'application/json'
            , error: function (jqXHR, textStatus, errorThrown) {
                layer.closeAll('loading');
                layer.alert('错误！请联系管理员。', { icon: 7 });
            }
            , type: "POST"
            , success: function (data) {
                layer.closeAll('loading');
                if (data.success) {
                    layer.msg(data.msg, { icon: 1, anim: 5, shadeClose: true, time: 600, shade: [0.1] });
                } else {
                    layer.msg(data.msg, { icon: 2, anim: 6, shadeClose: true, time: 1000, shade: [0.1] });
                };
                parameters.fun && parameters.fun(data);
            }
        });
    },
    // 数据表格删除
    d.remove = function (url, data, id) {
        // 说明：
        //     参数: url: String    后台链接
        //           data: String   参数
        //           id: String     数据表格内id
        $.getJSON(url, data, function (data) {
            if (data.code==200) {
                parent.layer.msg(data.msg, { icon: 1, time: 2000 });
                table.reload(id);
            } else {
                parent.layer.msg(data.msg, { icon: 2, time: 3000 });
            }
            
        });
    },
    // 添加 tab页
    d.addTab = function (name, url, id) {
        // 说明：
        //     参数: name: tab 标题
        //     参数: url: 地址
        //     参数: id: 自定义id（根据id判断是否村子tab）

        var ifom = "<iframe class='layui-anim layui-anim-upbit zdy-clear-iframe' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        (function fn(i) {
            if (i == 5) return;
            var tab = this.layui.jquery("[lay-filter='tab-main']");
            if (tab.length == 0) {
                fn.call(this.parent, ++i);
            } else {
                var d = tab.find("[lay-id=addTab" + id + "]").length;
                if (d === 0) {
                    this.layui.element.tabAdd('tab-main', {
                        title: name,
                        content: ifom,
                        id: "addTab" + id
                    });
                }
                this.layui.element.tabChange("tab-main", "addTab" + id);
            }
        }).call(parent, 0);
    },
    //带按钮的提示框
    d.warn = function (center, fun1, fun2) {
        parent.layui.layer.confirm(center, {
            skin: 'zdy-open' //样式类名
            , shade: [0.2]
            , btn: ['确定', '取消'] //按钮
        }, function (index) {
            parent.layui.layer.close(index);
            fun1 && fun1();
        }, function (index) { fun2 && fun2(); });
    },
    //警告提示框
    d.hint = function (center) {
        //name 提示的值
        layer.msg(center, { icon: 2, anim: 6, shadeClose: true, time: 1200, shade: [0.1] });
    },
    //成功提示框
    d.win = function (center){
        layer.msg(center, { icon: 1, anim: 5, shadeClose: true, time: 600, shade: [0.1] });
    }
    // 左侧菜单栏选择
    d.tabElect = function (id){
        var $oneself = $('[tab_id="tb_' + id + '"]'),
            $parent = $oneself.parentsUntil('ul');
        if (!$parent.hasClass('layui-nav-itemed')) {
            $parent.find(' > a').click();
        }
        $oneself.click();
    }

    //输出test接口
    exports('zdy', d);
}); 