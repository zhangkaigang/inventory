<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/5/13
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%><html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!--body头-->
    <%@include file="/commonBody.jsp"%>
    <!--body主体-->
    <div class="layui-body" style="margin: 1%">
        <!--有user:add权限才显示开通用户链接，没有该权限不显示，相当于if(hasPermission(user:add))-->
        <shiro:hasPermission name="user:add">
            <div class="layui-btn" data-type="btnAdd" id="btnAdd">开通用户</div>
        </shiro:hasPermission>
        <div style="display:none;" id="currentUserId"><shiro:principal property="id"></shiro:principal></div>
        <form>
            <div class="grid">
                <table class="layui-table" id="viewGrid" lay-filter="viewGrid"></table>
            </div>
        </form>

    </div>
    <!--body尾-->
    <%@include file="/commonBottom.jsp"%>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/user/userList.js"></script>

<script type="text/html" id="barDemo">
    <shiro:hasPermission name="user:edit">
        <a class="layui-btn layui-btn-xs" lay-event="btnEdit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="user:delete">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="btnDelete">删除</a>
    </shiro:hasPermission>
</script>
</body>
</html>
