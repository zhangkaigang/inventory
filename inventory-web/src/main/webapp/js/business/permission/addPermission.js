
layui.use(['form'], function(){
    //  Form模块全自动渲染，将原本普通的诸如select、checkbox、radio等元素重置为你所看到的模样
    var form = layui.form;

    // 取父窗口传递的参数
    var parentId = parent.param.parentId;
    $('#parentId').val(parentId);

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 取消
        btnCancel : function(){
            // 获取当前弹出层的等级
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },
        // 保存权限
        btnSave : function(){
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnData = commonFuns.$Ajax(contextPath+"/permission/addPermission.action", formVal);
                if(returnData.result == "SUCCESS"){
                    layer.alert("开通成功！", function(){
                        // 刷新父页面
                        window.parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        // 关闭弹出层
                        parent.layer.close(index);
                    });
                }else{
                    layer.alert("开通失败");
                    return false;
                }
                // layui的表单提交一定需要加这个false
                return false;
            });
        }
    };

});