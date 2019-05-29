<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/4/25
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%>
<script type="text/javascript">
    function getPermissionBtn(){
        var editBtn = '<shiro:hasPermission name="permission:edit"><a class="layui-btn layui-btn-xs" lay-event="btnEdit">编辑</a></shiro:hasPermission> ';
        var addChildBtn = '<shiro:hasPermission name="permission:add"><a class="layui-btn layui-btn-xs" lay-event="btnAddChild">添加子节点</a></shiro:hasPermission> ';
        var deleteBtn = '<shiro:hasPermission name="permission:delete"><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="btnDelete">删除</a></shiro:hasPermission> ';
        return editBtn + addChildBtn + deleteBtn;
    }
</script>
<html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!--body头-->
        <%@include file="/commonBody.jsp"%>
        <!--body主体-->
        <div class="layui-body" style="margin: 1%">
            <shiro:hasPermission name="permission:add">
                <button class="layui-btn" data-type="btnAdd" id="btnAdd">开通权限</button>
            </shiro:hasPermission>
            <div class="grid">
                <table class="layui-table" id="viewGrid" lay-filter="viewGrid"></table>
            </div>
        </div>
        <!--body尾-->
        <%@include file="/commonBottom.jsp"%>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/business/permission/permissionList.js"></script>
</body>
</html>
