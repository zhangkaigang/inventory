<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/4/23
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%>
<!DOCTYPE html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!--body头-->
        <%@include file="/commonBody.jsp"%>
        <!--body主体-->
        <div class="layui-body" style="margin: 1%">
            <button class="layui-btn" onclick="">开通角色</button>
            <div class="grid">
                <table class="layui-table" id="viewGrid"></table>
            </div>
        </div>
        <!--body尾-->
        <%@include file="/commonBottom.jsp"%>
    </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/role/roleList.js"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="btnEdit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="btnDelete">删除</a>
</script>
</body>
</html>
