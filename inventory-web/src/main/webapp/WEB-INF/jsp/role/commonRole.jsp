<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/5/28
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-layout layui-layout-admin">
    <!--body主体-->
    <div class="layer_self_wrap" style="margin: 1%">
        <form id="roleForm" class="layui-form" action="" method="post">
            <input type="hidden" id="id" name="id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">角色名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="roleName" id="roleName" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色编码：</label>
                <div class="layui-input-inline">
                    <input type="text" name="roleCode" id="roleCode" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色描述：</label>
                <div class="layui-input-block">
                    <input type="text" name="description" id="description" lay-verify="" placeholder="" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限：</label>
                <div class="layui-input-block">
                    <ul id="permissionTree" class="ztree" style="height: 300px;overflow-y: scroll;"></ul>
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
