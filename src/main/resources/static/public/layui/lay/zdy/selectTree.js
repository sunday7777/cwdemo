layui.define(['tree'],function (exports) {
    "use strict";
    var tree = layui.tree,
        A = function (d) {
            this.data = d;
        };

    A.prototype.init = function(d){
        var t = this,
            data = t.data,
            $el = $(data.elem),
            id = Math.random().toString(26).substr(2);

        $el.addClass("layui-hide");

        var code = $el.val(),
            state = false,
            name = "";
        if (!!code) {
            ;!function c(data) {
                if (state) return;
                for (var i = 0, len = data.length; i < len; i++) {
                    if (data[i][(t.data.value || 'attributes')] + "" == code + "") {
                        name = data[i].text;
                        state = true;
                        return;
                    }
                    if (!!data[i].children) {
                        if (data[i].children.length != 0) {
                            c(data[i].children);
                        }
                    }
                }
            }(data.nodes);
        }
        var nv = '<div class="layui-unselect layui-form-select">'+
                    '<div class="layui-select-title"><input id="'+ id +'-input" type="text" value="'+name+'" placeholder="' + (data.name || '请选择') + '" readonly class="layui-input layui-unselect"><i class="layui-edge"></i></div>'+
                    '<div class="zdy-codeSelect layui-anim layui-anim-upbit">'+
                        '<ul id="' + id +'"></ul>'+
                    '</div>'+
                '</div>';
        $el.after(nv);
        t.on(id+'-input');//绑定点击事件
        return t.tree(id);//生产tree;
    },
    A.prototype.tree = function(id){
        var t = this;
        return tree({
            elem: '#'+id, //ul 选择器
            check: t.data.check, //复选框（如果不需要则不写）
            checkboxStyle: "",
            click: function (d) { //点击节点回调
                t.treeOn(d,id);
                $('#'+id).parent('div').parent('div').removeClass('zdy-codeShow layui-form-selected zdy-codeSelectUp');
                t.data.onClick && t.data.onClick(d);
            },
            onchange: function (d) {//复选框选择时回掉
                // console.log(d);//返回所点击节点对应的数据
            },
            nodes: t.data.nodes
        });
    },
    A.prototype.on = function(id){
        var $d = $('#'+id);
        $d.on('focus',function(){
            var d = $(document),
                w = $(window);
            var h = d.height() - $d.offset().top - (d.height() - w.scrollTop() - w.height()) - $d.height();
            var $parent = $(this).parent('div').parent('div');
            $parent.addClass('zdy-codeShow layui-form-selected');
            if ($parent.find(".zdy-codeSelect").height() > h)$parent.addClass('zdy-codeSelectUp');
        });

        $(document).on('click', function (e) {
            if($(e.target).attr("id") != id)$d.parent('div').parent('div').removeClass('zdy-codeShow layui-form-selected zdy-codeSelectUp');
        });
    },
    A.prototype.treeOn = function(d,id){
        var t = this;
        $(t.data.elem).val(d[(t.data.value || 'attributes')]);
        $('#' + id + "-input").val(d.text);
    };

    //输出test接口
    exports('selectTree', function(d){
        if (typeof d.nodes == 'string'){
            $.getJSON(d.nodes, function (data) {
                d.nodes = data;
                var a = new A(d);
                return a.init();
            });
        }else{
            var a =  new A(d);
            return a.init();
        }
    });
}); 