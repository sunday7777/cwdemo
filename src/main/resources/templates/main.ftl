<#include "layout/top.ftl">
<#include "layout/nav.ftl">

    <!--********** 主体部分 Start **********-->
    <div class="layui-body">
        <div id="navDiv" class="layui-tab layui-tab-card" lay-allowClose="true" lay-filter="tab-main">
            <ul id="tabUL" class="layui-tab-title zdy-tab-title"></ul><!--tab页-->
            <div class="zdy-tab-nav">
                <div id="zdy-tab-nav-z1" class="zdy-tab-nav-z1 zdy-tab-hover"><i class="fa fa-chevron-left"></i></div>
                <div id="zdy-tab-nav-z2" class="zdy-tab-nav-z2 zdy-tab-hover"><i class="fa fa-chevron-right"></i></div>
                <div id="zdy-tab-nav-z4" class="zdy-tab-nav-z4 zdy-tab-hover"><i class="fa fa-refresh zdy_fa-rig5"></i>刷新</div>
                <div class="zdy-tab-nav-z3 zdy-tab-hover">
                    <ul class="layui-nav">
                        <li class="layui-nav-item zdy-nav-remove">
                            <a href="javascript:;"><i class="fa fa-hand-pointer-o zdy_fa-rig5"></i>常用操作</a>
                            <dl class="layui-nav-child">
                                <dd class="tab-nav-z"><a id="cy-dq" href="javascript:;"><i class="fa fa-close zdy_fa-rig5"></i>关闭当前选项卡</a></dd>
                                <dd class="tab-nav-z"><a id="cy-qt" href="javascript:;"><i class="fa fa-times-circle-o zdy_fa-rig5"></i>关闭其他选项卡</a></dd>
                                <dd class="tab-nav-z"><a id="cy-qb" href="javascript:;"><i class="fa fa-window-close-o zdy_fa-rig5"></i>关闭全部选项卡</a></dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="layui-tab-content"></div><!--内容区域-->
        </div>
    </div>
    <!--********** 主体部分 End **********-->

<#include "layout/bot.ftl">