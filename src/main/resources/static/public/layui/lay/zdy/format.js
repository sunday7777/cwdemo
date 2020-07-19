layui.define(function (exports) {
    "use strict";
    var d = {},
        A = function(){
            
        };
    d.timeFormat = function (e,fmt){
        var t = new Date(e),
            o = {
            "M+": t.getMonth() + 1, //月份 
            "d+": t.getDate(), //日 
            "h+": t.getHours(), //小时 
            "m+": t.getMinutes(), //分 
            "s+": t.getSeconds(), //秒 
            "q+": Math.floor((t.getMonth() + 3) / 3), //季度 
            "S": t.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (t.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
    A.prototype.time = function(d){
        $("input[zdy-timeFormat]").each(function () {
            var th = $(this),
                val = th.val(),
                f = th.attr("zdy-timeFormat");
            if (!!val) th.attr('value',d.timeFormat(val, f));
        });
    };

    var init =  new A();
    init.time(d);

    //输出test接口
    exports('format', d);
}); 