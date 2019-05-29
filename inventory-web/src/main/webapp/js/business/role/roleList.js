var cols = [[
    {field:'roleName', title: '角色名称'},
    {field:'description', title: '角色描述'},
    {field:'roleCode', title: '角色编码'},
    {field:'createDate', title: '添加时间'},
    {field:'wealth', title: '操作',fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
]];


layui.use('table', function(){
    var table = layui.table;
    // 初始化表格
    table.render({
        elem: '#viewGrid',
        url: contextPath + "/role/queryRoleList.action",
        method:'post',
        where:{},
        cols: cols,
        page: false,
        id : 'viewGrid'
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 开通角色(新增)
        btnAdd : function(){
            var url = contextPath + "/role/addRolePage.action"
            // 页面层
            layer.open({
                type: 2,
                title : '开通角色',
                area: ['500px', '600px'],
                content: url
            });
        }
    };

    // 监听行工具事件
    table.on('tool(viewGrid)', function(obj){
        var selectData = obj.data;
        if(obj.event === 'btnEdit'){
            // 编辑
            var url = contextPath + "/role/editRolePage.action";
            param = {
                selectData: selectData
            };
            // 页面层
            layer.open({
                type: 2,
                title : '编辑角色',
                area: ['500px', '600px'],
                content: url
            });
        }else if(obj.event === 'btnDelete'){
            // 删除
            layer.confirm('是否删除该角色!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnData = commonFuns.$Ajax(contextPath+"/role/deleteRole.action", {"id" : selectData.id});
                    commonFuns.dealResult(returnData);
                }
            );
        }
    });


});


