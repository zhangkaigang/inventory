<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/6/26
  Time: 21:24
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
                <label class="layui-form-label">日志名称</label>
                <div class="layui-input-inline">
                    <input id="logName" name="logName" autocomplete="off" class="layui-input" type="text"/>
                </div>

                <label class="layui-form-label">添加时间</label>
                <div class="layui-input-inline" style="width: 175px;">
                    <input  name="createDateStart" id="createDateStart" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input" type="text"/>
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 175px;">
                    <input name="createDateEnd" id="createDateEnd" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input" type="text"/>
                </div>

                <button class="layui-btn"  lay-submit="" data-type="btnSearch" lay-filter="btnSearch"><i class="layui-icon">&#xe615;</i>查询</button>
            </div>
        </form>

        <shiro:hasPermission name="businessLog:clear">
            <div class="layui-btn icon-btn layui-btn-danger" data-type="btnClear" id="btnClear">
                <i class="layui-icon">&#xe67d;</i>清空日志
            </div>
        </shiro:hasPermission>
        <div class="grid">
            <table class="layui-table" id="viewGrid" lay-filter="viewGrid"></table>
        </div>
    </div>
    <!--body尾-->
    <%@include file="/commonBottom.jsp"%>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/business/log/businessLogList.js"></script>
    <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="btnView">查看详情</a>
    </script>
</body>
</html>
