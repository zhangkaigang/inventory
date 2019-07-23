<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/7/15
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%><html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">


<div class="layui-layout layui-layout-admin">
    <!--body主体-->
        <div  class="layer_self_wrap" style="margin: 1%">
            <div class="grid">
                <table class="layui-table" id="viewGrid" lay-filter="viewGrid"></table>
            </div>
        </div>

</div>
<!--body尾-->
<%@include file="/commonBottom.jsp"%>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/develop/importLog.js"></script>
</body>
</html>
