var cols = [[
    {field:'loginName', title: '登录名'},
    {field:'realName', title: '真实姓名'},
    {field:'createDate', title: '添加时间'},
    {field:'isJob', title: '是否在职', width:95,align:'center', templet: function (d) {
            if(d.isJob == '0'){
                return '<input name="isJobTpl" id="isJobTpl" type="checkbox" lay-skin="switch" lay-text="在职|离职" checked lay-filter="isJobTpl"/>';
            }else if(d.isJob == '1'){
                return '<input name="isJobTpl" id="isJobTpl" type="checkbox" lay-skin="switch" lay-text="在职|离职" lay-filter="isJobTpl"/>';
            }else{
                return "";
            }
        }
    },
    {field:'wealth', title: '操作',fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
]];

layui.use(['table'], function() {
    var table = layui.table;
    // 初始化表格
    table.render({
        elem: '#viewGrid',
        url: contextPath + "/user/queryUserList.action",
        method:'post',
        where:{},
        cols: cols,
        page: {
            limit:10,
            limits:[10, 100, 150, 200]
        },
        id : 'viewGrid'
    });
    commonFuns.renderForm();
    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 开通角色(新增)
        btnAdd : function(){
            var url = contextPath + "/user/addUserPage.action"
            // 页面层
            layer.open({
                type: 2,
                title : '开通用户',
                area: ['500px', '600px'],
                content: url
            });
        }
    };

    // 监听行工具事件
    table.on('tool(viewGrid)', function(obj){
        var selectData = obj.data;
        if(obj.event === 'btnEdit'){
            var url = contextPath + "/user/editUserPage.action?id=" + selectData.id;
            param = {
                selectData: selectData
            };
            // 页面层
            layer.open({
                type: 2,
                title : '编辑用户',
                area: ['500px', '600px'],
                content: url
            });
        }else if(obj.event === 'btnDelete'){
            // 删除用户
            layer.confirm('是否删除该用户!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnData = commonFuns.$Ajax(contextPath + "/user/deleteUser.action", {"id" : selectData.id});
                    commonFuns.dealResult(returnData);
                }
            );
        }
    });
});