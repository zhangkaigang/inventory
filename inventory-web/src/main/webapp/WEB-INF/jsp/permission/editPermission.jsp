<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/5/28
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%>
<html>
<head>
    <%@include file="/commonHead.jsp"%>
    <script type="text/javascript">
        var selectData = parent.param.selectData;
    </script>
</head>
<body class="layui-layout-body">
<%@include file="commonPermission.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/permission/editPermission.js"></script>
</body>
</html>
