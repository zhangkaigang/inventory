<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/4/25
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%>
<html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!--body主体-->
    <div class="layer_self_wrap" style="margin: 1%">
        <form id="roleForm" class="layui-form" action="" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">角色名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="roleName" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="code" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色描述：</label>
                <div class="layui-input-block">
                    <input type="text" name="description" lay-verify="" placeholder="" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限：</label>
                <div class="layui-input-block">
                    <ul id="permissionList"></ul>
                </div>
            </div>
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="btnSave">保存</button>
                <button class="layui-btn layui-btn-primary" id="btnCancel">关闭</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/role/addRole.js"></script>
</body>
</html>
