<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/4/26
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%>
<html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!--body主体-->
        <div class="layer_self_wrap" style="margin: 1%">
            <form id="permForm" class="layui-form layui-form-pane" method="post" action="" style="margin-top: 20px;">
                <input id="type" type="hidden" name="type"/>
                <input id="pid" type="hidden" name="pid"/>
                <input id="id" type="hidden" name="id"/>
                <div class="layui-form-item">
                    <label class="layui-form-label">权限名称</label>
                    <div class="layui-input-inline">
                        <input name="permissionName" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                    </div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">权限分类</label>
                    <div class="layui-input-block">
                        <input type="radio" name="itemType" value="0" title="菜单" checked=""/>
                        <input name="itemType" value="1" title="功能" type="radio"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">权限路径</label>
                    <div class="layui-input-inline">
                        <input name="permissionUrl" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">权限编码</label>
                    <div class="layui-input-inline">
                        <input name="permissionCode" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">权限顺序</label>
                    <div class="layui-input-inline">
                        <input name="sort" autocomplete="off" class="layui-input" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    </div>
                    <div class="layui-form-mid layui-word-aux">请填写大于0的整数数字</div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">描述</label>
                    <div class="layui-input-block">
                        <textarea name="description" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left: 10px;">
                        <button class="layui-btn"  lay-submit="" lay-filter="permSubmit">提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
