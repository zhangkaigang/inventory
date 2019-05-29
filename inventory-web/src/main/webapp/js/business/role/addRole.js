var setting = {
    check: {
        enable: true,
        chkboxType:{ "Y":"ps", "N":"ps"}
    },
    async: {
        enable: true,//采用异步加载
        dataFilter: ajaxDataFilter,    //预处理数据
        url : contextPath + "/permission/queryPermissionList.action",
        dataType : "json"
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
    },
    callback : {
        onAsyncSuccess: zTreeOnAsyncSuccess //异步加载完成调用
    }
};

/* 获取返回的数据，进行预操作 */
function ajaxDataFilter(treeId, parentNode, responseData) {
    return responseData.data;
};

//异步加载完成时运行，此方法将所有的节点打开
function zTreeOnAsyncSuccess(event, treeId, msg) {
    var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
    treeObj.expandAll(true);
}

layui.use(['form'], function() {
    //  Form模块全自动渲染，将原本普通的诸如select、checkbox、radio等元素重置为你所看到的模样
    var form = layui.form;
    // 初始化权限树
    $.fn.zTree.init($("#permissionTree"), setting, null);
    // 重新渲染表单
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
        // 保存权限
        btnSave : function(){
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
                var returnData = commonFuns.$Ajax(contextPath+"/role/addRole.action", formVal);
                commonFuns.dealChildPageResult(returnData);
                return false;
            });
        }
    };
});