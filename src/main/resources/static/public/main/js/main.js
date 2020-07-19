layui.use(["layer","element",'zdy'],function(){
    var layer = layui.layer,
        element = layui.element,
    	zdy = layui.zdy;
	
	// 注销
	$('#exitID').click(function(){
		window.location.href = "/j_spring_security_logout";
	});

	// 基础信息
	$('#basicsID').click(function(){
		zdy.addTab('<i class="fa fa-address-card">&nbsp;&nbsp;</i>用户基本信息', '/rest/sys/user/toBasics', 'add111');
	});
	// 系统设置
	$('#settingID').click(function () {
		zdy.addTab('<i class="fa fa-list-ol">&nbsp;&nbsp;</i>系统设置', '/rest/sys/settings/toSettings', 'add1112');
	});
	 // 登陆密码修改
	 $('#updateID').click(function (){
	 	zdy.addTab('<i class="fa fa-list-ol">&nbsp;&nbsp;</i>登陆密码修改', '/rest/sys/settings/toUpdate', 'add1113');
	 });
	

	// 消息下拉点击事件
	$('#hintID > li').click(function() {
		element.tabChange("tab-main", "sy");

		var tabName = $(this).attr('tab');
		$('#home-pageID')[0].contentWindow.f_details(tabName);
	});

	// 侧边栏滚动调
	$('#scroID').mCustomScrollbar();

//********** 加载时执行 Strat **********//

    //***** 加载首页 Strat *****//
    element.tabAdd('tab-main', {
        title: "<i class='fa fa-home fa-r-5 fa-lg'></i>首页",
        content: "<iframe id='home-pageID' class='layui-anim layui-anim-upbit zdy-clear-iframe' frameborder='0' scrolling='auto' src='home/index'></iframe>",
        id: "sy"
    });
    element.tabChange("tab-main", "sy");
	//***** 加载首页 End *****//

	//***** 提示框方法 Strat *****// 
	$.getJSON('/rest/sys/messageUser/findNumber', function(namber) {
		$('#badge').text(namber);
	});
	$.getJSON('/rest/sys/messageUser/findNumberId?ids=' + '7,8,10,12,13,14', function(namber){
        $('#man_id_span').text(namber);
    });
	$.getJSON('/rest/sys/messageUser/findNumberId?ids=' + '2', function(namber){
        $('#vehicle_id_span').text(namber);
    });
	$.getJSON('/rest/sys/messageUser/findNumberId?ids=' + '3', function(namber){
        $('#asset_id_span').text(namber);
    });
	$.getJSON('/rest/sys/messageUser/findNumberId?ids=' + '4', function(namber){
        $('#pact_id_span').text(namber);
    });
	$.getJSON('/rest/sys/messageUser/findNumberId?ids=' + '5', function(namber){
        $('#calendar_id_span').text(namber);
    });
	$.getJSON('/rest/sys/messageUser/findNumberId?ids=' + '6', function(namber){
        $('#meeting_id_span').text(namber);
    });
});