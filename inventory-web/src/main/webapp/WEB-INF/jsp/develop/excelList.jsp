<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/7/3
  Time: 19:10
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

        <div class="layui-btn layui-btn-normal" data-type="btnImport" id="btnImport">导入</div>

        <div class="grid">
            <table class="layui-table" id="viewGrid" lay-filter="viewGrid"></table>
        </div>
    </div>
    <!--body尾-->
    <%@include file="/commonBottom.jsp"%>


</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/develop/excelList.js"></script>

</body>
</html>
