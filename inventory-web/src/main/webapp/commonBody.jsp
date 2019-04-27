<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--头-->
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <a href="javascript:;" class="layui-hide-xs"><a href="/home" style="font-weight: bold;"><div class="layui-logo">后台管理系统</div></a>
        </a>
        <a href="javascript:;" class="layui-hide-xs">
            <div class="switchMenu" style="display: none;"><i class="fa fa-indent"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/images/logo.png" class="layui-nav-img"/>
                    ${userSession.realName}
                </a>
                <dl class="layui-nav-child">
                    <!--<dd><a href="">基本资料</a></dd>-->
                    <dd id="updUsePwdDd" class="layui-this"><a href="javascript:;" onclick="updateUsePwd();">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/logout.action">注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域 -->
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/head.js"></script>
<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
</div>
<div class="site-mobile-shade"></div>