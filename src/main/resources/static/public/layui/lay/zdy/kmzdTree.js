layui.define(function(exports){
	"use strict";
	var fun = function(el,url){
		this.el = el;
		this.url = url;
	};
	fun.prototype.init = function(){
		var t = this,
			url = t.url,
			el = t.el,
			id = el.replace(/[^a-z]+/ig, "");
		$.ajax({
			url: url,
			async: false,
			dataType: "json",
			success: function (data) {
				var tre = $(el);
				var xh = 1, //序号
					ij = 0, //级别
					yz = 14, //每次添加padding-left的距离
					jl = 15; //默认padding-left
				var y_cen = "<i class='i1 fa fa-caret-right zdy_fa-rig5'></i><i class='i2 fa fa-folder zdy_fa-rig5'></i>";
				var z_cen = "<i class='i1 fa fa-caret-down zdy_fa-rig5'></i><i class='i2 fa fa-folder-open-o zdy_fa-rig5'></i>";
				var w_cen = "<i class='fa fa-file-text-o zdy_fa-rig5'></i>";

				tre.empty();
				addcent(tre, data);

				function addcent(dom, dat) {
					$.each(dat, function (key, val) {
						ij = val.attributes.length / 2;
						var nv = "<li><a class='tre_a' href='javaScript:;'>" +
							"<div>" + (xh++) + "</div>" +
							"<div class='tre_clik' style='padding-left:" + (jl + yz * ij) + "px'>" + (val.children.length == 0 ? w_cen : z_cen) + val.kmmc + "</div>" +
							"<div>" + val.attributes + "</div>" +
							"<div>" + (val.kmzx || "") + "&nbsp;</div>" +
							"<div id='tre_" + id + "-but-" + val.id + "'></div>" +
							"</a><ul id='tre_" + id + "-ul" + val.id + "'></ul></li>";
						dom.append(nv);

						var $add = $("<button class='layui-btn layui-btn-xs'>添加下一级</button>"),
							$edit = $("<button class='layui-btn layui-btn-xs'>编辑</button>"),
							$remove = $("<button class='layui-btn layui-btn-danger layui-btn-xs'>删除</button>");

						$add.click(function(){
							t.data.addClick.call("待完善",val);
						});
						$edit.click(function () {
							t.data.editClick.call("待完善", val);
						});
						$remove.click(function () {
							t.data.removeClick.call("待完善", val);
						});
						
						var con = $("#tre_" + id + "-but-" + val.id).append($add).append($edit);
						if (ij != 1) con.append($remove);

						addcent($("#tre_" + id + "-ul" + val.id), val.children);
					});
				}
				$(".tre_a > .tre_clik").click(function () {
					var th = $(this).parent(),
						u = th.next("ul"),
						i1 = th.find(".i1"),
						i2 = th.find(".i2");

					if (u.is(":hidden")) {
						u.slideDown("fast");
						i1.attr("class", "i1 fa fa-caret-down zdy_fa-rig5");
						i2.attr("class", "i2 fa fa-folder-open-o zdy_fa-rig5");
					} else {
						u.slideUp("fast");
						i1.attr("class", "i1 fa fa-caret-right zdy_fa-rig5");
						i2.attr("class", "i2 fa fa-folder zdy_fa-rig5");
					}
				});
			}
		});
		return t.f();
	},
	fun.prototype.data = {
		addClick:function(){},
		editClick:function(){},
		removeClick:function(){}
	},
	fun.prototype.f = function(){
		var t = this;
		return {
			reload: function () {t.init()},
			addClick: function (d) {t.data.addClick = d;},
			editClick: function (d) {t.data.editClick = d;},
			removeClick: function (d) {t.data.removeClick = d;}
		}
	}

	exports("kmzdTree",function(el,url) {
		var f = new fun(el,url);
		return f.init();
	});
}); 