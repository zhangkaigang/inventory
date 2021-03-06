<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/1/21
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="/commonHead.jsp"%>
    <style type="text/css">
        .layui-form-checkbox[lay-skin=primary]{height:fit-content !important;}
    </style>
</head>
<body onload="init()">
<div class="layui-container">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 6%">
        <legend style="margin-left: 40%"><span style="font-weight: bold;font-size:22px;">后台管理系统</span></legend>
    </fieldset>
    <form id="loginForm" class="layui-form" method="post" action="${pageContext.request.contextPath}/login.action" style="float:right;">
        <input type="hidden" id="errorMsg" value="${msg}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input id="loginName" name="loginName" lay-verify="required" autocomplete="off" class="layui-input" type="tel"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密&nbsp;&nbsp;码</label>
            <div class="layui-input-inline">
                <input id="password" name="password" lay-verify="required" autocomplete="off" class="layui-input" type="password"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input id="code" name="code" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                </div>
                <div class="layui-input-inline" style="width: 120px;">
                    <img src="${pageContext.request.contextPath}/validateCode.jsp" id="imgCode" alt="" width="120" height="38"
                         onclick="codeFresh();" align='absMiddle' />
                </div>
            </div>
        </div>
        <%--<div class="layui-form-item">
            <label class="layui-form-label"></label>
            <input type="checkbox" name="rememberMe" lay-skin="primary" title="记住用户名和密码"/>
        </div>--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button id="login" class="layui-btn" lay-submit="" lay-filter="login">登录</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>

    </form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/login.js"></script>

</body>
</html>
