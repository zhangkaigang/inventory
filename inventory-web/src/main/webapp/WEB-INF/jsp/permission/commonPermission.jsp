<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="layui-layout layui-layout-admin">
    <!--body主体-->
    <div class="layer_self_wrap" style="margin: 1%">
        <form id="permissionForm" class="layui-form layui-form-pane" method="post" action="" style="margin-top: 20px;">
            <input type="hidden" name="id" id="id" value="">
            <input type="hidden" name="parentId" id="parentId" value="">
            <div class="layui-form-item">
                <label class="layui-form-label">权限名称</label>
                <div class="layui-input-inline">
                    <input name="permissionName" id="permissionName" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                </div>
            </div>
            <div class="layui-form-item" pane="">
                <label class="layui-form-label">权限分类</label>
                <div class="layui-input-block">
                    <input type="radio" name="itemType" id="itemType0" value="0" title="菜单" checked=""/>
                    <input type="radio" name="itemType" id="itemType1" value="1" title="功能"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限路径</label>
                <div class="layui-input-inline">
                    <input name="permissionUrl" id="permissionUrl" autocomplete="off" class="layui-input" type="text"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限编码</label>
                <div class="layui-input-inline">
                    <input name="permissionCode" id="permissionCode" lay-verify="required" autocomplete="off" class="layui-input" type="text"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限顺序</label>
                <div class="layui-input-inline">
                    <input name="sort" id="sort" autocomplete="off" class="layui-input" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')"
                           onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                </div>
                <div class="layui-form-mid layui-word-aux">请填写大于0的整数数字</div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea name="description" id="description" placeholder="请输入内容" class="layui-textarea"></textarea>
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