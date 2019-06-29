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
        <!--查询表单-->
        <form id="searchForm" class="layui-form layui-form-pane" method="post" action="" style="margin-top: 20px;">
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input id="realName" name="realName" autocomplete="off" class="layui-input" type="text"/>
                </div>
                <button class="layui-btn"  lay-submit="" data-type="btnSearch" lay-filter="btnSearch"><i class="layui-icon">&#xe615;</i>查询</button>
            </div>
        </form>

        <!--有user:add权限才显示开通用户链接，没有该权限不显示，相当于if(hasPermission(user:add))-->
        <shiro:hasPermission name="user:add">
            <div class="layui-btn layui-btn-normal" data-type="btnAdd" id="btnAdd"><i class="layui-icon">&#xe654;</i>开通用户</div>
        </shiro:hasPermission>
        <div style="display:none;" id="currentUserId"><shiro:principal property="id"></shiro:principal></div>
        <!--添加一个form为了监听switch事件-->
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
