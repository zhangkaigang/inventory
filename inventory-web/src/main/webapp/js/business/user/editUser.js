layui.use(['form'], function() {
    var form = layui.form;
    $("#userForm input").each(function(index, element) {
        $("#"+element.id).val(selectData[element.name]);
    });
    commonFuns.renderForm();

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 取消
        btnCancel: function () {
            commonFuns.btnCancel();
        },
        // 保存用户
        btnSave : function() {
            // 监听表单提交
            form.on('submit(btnSave)', function (data) {
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
                var returnData = commonFuns.$Ajax(contextPath+"/user/editUser.action", formVal);
                commonFuns.dealChildPageResult(returnData);
                return false;
            });
        }
    };
});