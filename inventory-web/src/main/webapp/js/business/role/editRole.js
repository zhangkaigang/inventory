var setting = {
    check: {
        enable: true,
        chkboxType:{ "Y":"ps", "N":"ps"}
    },
    data: {
        key : {
            title : "permissionName", //鼠标悬停显示的信息
            name : "permissionName" //网页上显示出节点的名称
        },
        simpleData: {
            enable: true,
            idKey: "id", //修改默认的ID为自己的ID
            pIdKey: "parentId",//修改默认父级ID为自己数据的父级ID
            rootPId: 0     //根节点的ID
        }
    }
};


layui.use(['form'], function() {
    var form = layui.form;
    $("#roleForm input").each(function(index, element) {
        $("#"+element.id).val(selectData[element.name]);
    });

    var returnData = commonFuns.$Ajax(contextPath+"/role/queryPermissionsByRoleId.action", {'roleId' : selectData.id});
    if(returnData.result == 'SUCCESS') {
        var treeNode = returnData.data;
        $.fn.zTree.init($("#permissionTree"), setting, treeNode);
        var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
        treeObj.expandAll(true);
    }
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
        // 保存
        btnSave: function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                // 获取选中的权限
                var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
                var nodes = treeObj.getCheckedNodes(true);
                // 选中的复选框
                var nodeIds =new Array();
                for (var i = 0; i < nodes.length; i++) {
                    nodeIds.push(nodes[i].id);
                }
                //校验是否授权
                var permissionIds = nodeIds.join(",");
                if(permissionIds == null || permissionIds ==''){
                    layer.alert("请给该角色添加权限菜单！")
                    return false;
                }
                var formVal = data.field;
                formVal.permissionIds = permissionIds;
                var returnData = commonFuns.$Ajax(contextPath+"/role/editRole.action", formVal);
                commonFuns.dealChildPageResult(returnData);
                return false;
            });
        }
    };


});