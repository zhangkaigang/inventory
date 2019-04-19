<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>
    <head>
        <title>后台管理系统</title>
        <%@include file="common.jspf"%>
        <script type="text/javascript">
            var menuList = <%= menuListArray%>;
        </script>
    </head>
    <body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!--头-->
        <div class="layui-layout layui-layout-admin">
            <div class="layui-header">
                <a href="javascript:;" class="layui-hide-xs"><a href="/home" style="font-weight: bold;"><div class="layui-logo">后台管理系统</div></a>
                </a>
                <a href="javascript:;" class="layui-hide-xs">
                    <div class="switchMenu" style="display: none;"><i class="fa fa-indent"></i></div>
                </a>
                <!-- 头部区域（可配合layui已有的水平导航） -->
                <%--<ul class="layui-nav layui-layout-left">
                    <li class="layui-nav-item"><a href="">控制台</a></li>
                    <li class="layui-nav-item"><a href="">商品管理</a></li>
                    <li class="layui-nav-item"><a href="">用户</a></li>
                    <li class="layui-nav-item">
                        <a href="javascript:;">其它系统</a>
                        <dl class="layui-nav-child">
                            <dd><a href="">邮件管理</a></dd>
                            <dd><a href="">消息管理</a></dd>
                            <dd><a href="">授权管理</a></dd>
                        </dl>
                    </li>
                </ul>--%>
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/head.js"></script>
        <!-- 移动导航 -->
        <div class="site-tree-mobile layui-hide">
            <i class="layui-icon">&#xe602;</i>
        </div>
        <div class="site-mobile-shade"></div>


        <div class="layui-body">
            <div class="layui-container">
                <div class="layui-row">
                    <div class="layui-col-xs9">
                        &nbsp;
                        <h1 style="padding-top:50px;line-height:2.0;">
                            <shiro:principal property="realName"></shiro:principal>，欢迎您登录
                        </h1>
                    </div>
                </div>
            </div>
        </div>
        <!--底部-->
        <div class="layui-footer">

        </div>
    </div>
    </body>
</html>