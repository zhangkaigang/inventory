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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript">
        var selectData = parent.param.selectData;

    </script>
</head>
<body class="layui-layout-body">
<%@include file="commonRole.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/role/editRole.js"></script>
</body>
</html>
