layui.use(["layer","element","zdy"],function(){
    var layer = layui.layer,
		element = layui.element,
		zdy = layui.zdy;

	//***** 设置本地存储吧tab页 默认 left 存储下来 tab也超出滑动需要 Strat *****//
	layui.data('tab', {
		key: 'left',
		value: 41
	});
	//***** 设置本地存储吧tab页 默认 left 存储下来 tab也超出滑动需要  End *****//

    //********** 菜单收缩 Strat **********//
    $("#side-but").click(function(){
    	var side = $(".layui-side"),
    		bod = $(".layui-body"),
			footer = $(".layui-footer"),
			$sid = $("#side-but");
    	
    	if(side.width() > 60){
    		side.animate({"width":"50px"},500,function(){
    			side.find("span").hide();
    		});
    		bod.animate({"left":"50px"},500);
			footer.animate({"left":"50px"},500);
			side.find("ul.layui-nav").animate({ "margin-left": "-5px"}, 500);
			$sid.animate({"width": "50px" }, 500);			
    	}else{
    		side.find("span").show();
    		side.animate({"width":"200px"},500);
    		bod.animate({"left":"200px"},500);
			footer.animate({"left":"200px"},500);
			side.find("ul.layui-nav").animate({ "margin-left": "0"}, 500);
			$sid.animate({ "width": "200px" }, 500);
    	}
    });
    //********** 菜单收缩 End **********//

	//********** 添加菜单 Strat **********//
	$.getJSON("/menu/getMenuTreeByUser",function(data){
    	var ul = $("ul[lay-filter='tab-nav']");
    	ul.empty();
    	$.each(data,function(key,val){
			var lis = "",
				pd = typeof (val.children) == 'undefined' ? false : val.children.length == 0 ? false : true,
				sta = val.state == 'open' ? 'layui-nav-itemed' : '',
				tabid = pd ? '' : "tab_id='tb_" + val.id + "'";
			lis = "<li class='layui-nav-item " + sta + "' " + tabid + ">";
			if (pd) {
				lis += "<a href='javascript:;'><i class='fa fa-r-5 fa-fw " + val.iconCls + "'></i><span>" + val.text + "</span></a>" +
					"<dl class='layui-nav-child'>";
				for (var i = 0; i < val.children.length; i++) {
					lis += "<dd tab_id='tb_" + val.children[i].id + "'><a class='zdyNavClick' href='javascript:;'' tab_url='" + val.children[i].attributes + "'><i id='navClick-" + val.children[i].id + "' class='fa fa-r-10 fa-fw " + val.children[i].iconCls + "'></i><span>" + val.children[i].text + "</span></a></dd>"
				}
				lis += "</dl></li>";
			} else {
				lis += "<a class='zdyNavClick' tab_url='" + val.attributes + "' href='javascript:;'><i class='fa fa-r-5 fa-fw " + val.iconCls + "'></i><span>" + val.text + "</span></a>";
				lis += "</li>"
			}
			ul.append(lis);
    	});

		element.render('nav');
		$(".zdyNavClick").on("mouseenter mouseleave", function (e) {
			if (e.type == "mouseenter"){
				if ($(".layui-side").width() < 60) {
					var th = $(this);
					layer.tips(th.text(), "#" + th.find("i").attr("id"), { time:0});
				}
			}else{
				layer.closeAll('tips');
			}
		});

		// 领导驾驶舱使用
		// 点击标题时跳转到系统页面,在进入响应的菜单
		// 实现方法 在url地址内入‘falg’参数 正则表达式给取出来 在用 ‘zdy’中的方法跳转
		(function(){
			// 获取url地址内的参数
			function getUrlParams(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = decodeURI(window.location.search).substr(1).match(reg);
				if (r != null) return r[2]; return null;
			}
			var falg = getUrlParams('falg');
			if (falg) {
				layui.use(['zdy'], function () {
					layui.zdy.tabElect(falg);
				});
			}
		}());
    });
    //********** 添加菜单 End **********//
	
    //********** 添加tab页 Strat **********//
	element.on('nav(tab-nav)', function(elem){
        var el = $(elem),
            tab_id = el.attr("tab_id"),
            i = "<i class='"+el.find("i").attr("class")+"'></i>"+el.find("span").text(),
			ifom = "<iframe id='ifrm" + tab_id + "' class='layui-anim layui-anim-upbit zdy-clear-iframe' frameborder='0' scrolling='auto' src='"+el.find("a").attr("tab_url")+"'></iframe>";
		ifom += "<script>document.getElementById('ifrm" + tab_id +"').onload = function(){layui.use(['layer'], function () { layui.layer.closeAll('loading');});}</script>";
        var tab = $("[lay-filter='tab-main']").find("[lay-id="+tab_id+"]");
		if (tab.length === 0){
			layer.load();
			element.tabAdd('tab-main', {
				title: i,
				content: ifom,
                id: tab_id
			});
        }
		element.tabChange("tab-main", tab_id);
		addNav(tab.position());//当tab超出时的处理
	});
	//当tab超出时
	function  addNav(position) {
		var $navDiv = $("#navDiv"),
			$tabUL = $("#tabUL"),
			divw = $navDiv.width(),
			ulw = $tabUL.width(),
			lef = 40,
			right = 180;
		var ca = divw - lef - right - ulw;
		if (position && ulw > divw) ca = Math.max(ca, (-position.left + 42));
		if (ca < 0){
			$tabUL.css("left", ca + "px");
			layui.data('tab', {
				key: 'left',
				value: ca
			});
		}
	}
	//********** 添加tab页 End **********//

	//********** tab左右滑动箭头点击事件 Strat **********//
	$("#zdy-tab-nav-z1").click(function(){
		var d = layui.data('tab').left;
		if (d != undefined && d < 41) {
			var dm = Math.min((d + 50), 41);
			$("#tabUL").css("left", dm + "px");
			layui.data('tab', {
				key: 'left',
				value: dm
			});
		}
	});
	$("#zdy-tab-nav-z2").click(function () {
		var $navDiv = $("#navDiv"),
			$tabUL = $("#tabUL"),
			divw = $navDiv.width(),
			ulw = $tabUL.width(),
			lef = 40,
			right = 180;
		var zong = divw - lef - right;
		var d = layui.data('tab').left;
		if (d != undefined && ulw > zong){
			var dm = Math.max((d - 50), zong-ulw);
			$tabUL.css("left", dm + "px");
			layui.data('tab', {
				key: 'left',
				value: dm
			});
		}
	});
	//********** tab左右滑动箭头点击事件 End **********//
	
	//********** 刷新点击事件 Strat **********//
	$("#zdy-tab-nav-z4").click(function () {
		var $navDiv = $("#navDiv").find(".layui-tab-content .layui-show iframe");
		$navDiv.attr('src', $navDiv.attr('src'));
	});
	//********** 刷新点击事件 End **********//

	//********** 常用操作点击事件 Strat **********//
	//当前
	$("#cy-dq").click(function(){
		var laythis = $("#tabUL").find("li.layui-this"),
			layid = laythis.attr("lay-id");
		if(layid != "sy"){
			element.tabDelete('tab-main', layid);
			var $navDiv = $("#navDiv"),
				$tabUL = $("#tabUL"),
				divw = $navDiv.width(),
				ulw = $tabUL.width(),
				lef = 40,//左边按钮的宽度
				right = 180;//右边所有按钮的宽度
			var zong = divw - lef - right;
			var d = layui.data('tab').left;
			if (d != undefined && ulw > zong) {
				var dm = Math.max((d - laythis.width()), zong - ulw);
				$tabUL.css("left", dm + "px");
				layui.data('tab', {key: 'left',value: dm});
			}else{
				$tabUL.css("left", "41px");
			}
		}else{
			zdy.hint('首页不能关闭！')
		}
	});
	//其他
	$("#cy-qt").click(function () {
		var $ul = $("#tabUL");
		$ul.find("li").each(function(key,val){
			var nameID = $(this).attr("lay-id");
			if (nameID != "sy" && this.className != "layui-this") element.tabDelete('tab-main', nameID);
		});
		$ul.css("left", "41px");
	});
	//全部
	$("#cy-qb").click(function () {
		var $ul = $("#tabUL");
		$ul.find("li").each(function (key, val) {
			var nameID = $(this).attr("lay-id");
			if (nameID != "sy") element.tabDelete('tab-main', nameID);
		});
		$ul.css("left", "41px");
	});
	//********** 常用操作点击事件 End **********//
	
});