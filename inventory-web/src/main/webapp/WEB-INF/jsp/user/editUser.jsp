<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/5/30
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%><html>
<head>
    <%@include file="/commonHead.jsp"%>
    <style type="text/css">
        /*覆盖样式*/
        .layui-form-checkbox[lay-skin=primary] {
            height: 10px !important;
        }
    </style>
    <script type="text/javascript">
        var selectData = parent.param.selectData;
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!--body主体-->
    <div  class="layer_self_wrap" style="margin: 1%">
        <form id="userForm" class="layui-form layui-form-pane" method="post" action="" style="margin-top: 20px;">
            <input type="hidden" id="id" name="id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">登录名</label>
                <div class="layui-input-inline">
                    <input id="loginName" name="loginName" lay-verify="required" autocomplete="off"
                           class="layui-input layui-disabled" type="text" disabled/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input id="realName" name="realName" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">授予角色</label>
                <div class="layui-input-block" id="roleDiv" style="height: 200px; overflow-y: auto" >
                    <c:forEach items="${roleList }" var="role">

                        <c:if test="${empty existRolesStr}">
                            <input type='checkbox' name='roleId' value="${role.id}" title="${role.roleName}" lay-skin='primary'/>
                        </c:if >

                        <c:if test="${not empty existRolesStr}">
                                <c:choose>
                                    <c:when test="${fn:contains(existRolesStr, role.id)}">
                                        <input type='checkbox' name='roleId' value="${role.id}" title="${role.roleName}" lay-skin='primary' checked/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type='checkbox' name='roleId' value="${role.id}" title="${role.roleName}" lay-skin='primary'/>
                                    </c:otherwise>
                                </c:choose>
                        </c:if >

                    </c:forEach>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block" style="margin-left: 10px;">
                    <button class="layui-btn"  lay-submit="" data-type="btnSave" lay-filter="btnSave">提交</button>
                    <div class="layui-btn layui-btn-primary" data-type="btnCancel">取消</div>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/user/editUser.js"></script>
</body>
</html>