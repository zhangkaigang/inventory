layui.use(['form'], function() {
    //  Form模块全自动渲染，将原本普通的诸如select、checkbox、radio等元素重置为你所看到的模样
    var form = layui.form;

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 取消
        btnCancel: function () {
            // 获取当前弹出层的等级
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },
        // 保存用户
        btnSave : function(){
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                // 选中的角色
                var array = new Array();
                $("input:checkbox[name='roleId']:checked").each(function(i){
                    array[i] = $(this).val();
                });
                //校验是否授权
                var roleIds = array.join(",");
                if(roleIds==null || roleIds==''){
                    layer.alert("请您给该用户添加对应的角色！")
                    return false;
                }
                formVal.roleIds = roleIds;
                var returnData = commonFuns.$Ajax(contextPath + "/user/addUser.action", formVal);
                commonFuns.dealChildPageResult(returnData);
                return false;
            });
        }
    };
});