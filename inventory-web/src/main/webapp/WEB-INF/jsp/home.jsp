<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%>
    <head>
        <title>后台管理系统</title>
        <%@include file="/commonHead.jsp"%>
    </head>
    <body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!--body头-->
        <%@include file="/commonBody.jsp"%>
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
        <!--body尾-->
        <%@include file="/commonBottom.jsp"%>
    </div>
    </body>
</html>