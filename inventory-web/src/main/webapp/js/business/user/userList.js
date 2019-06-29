var cols = [[
    {field:'loginName', title: '登录名'},
    {field:'realName', title: '真实姓名'},
    {field:'createDate', title: '添加时间'},
    {field:'isJob', title: '是否在职', width:95,align:'center', templet: function (d) {
            if(d.isJob == '0'){
                return '<input name="'+d.realName+'" value="'+d.id+'" id="isJobTpl" type="checkbox" lay-skin="switch" lay-text="在职|离职" checked lay-filter="isJobTpl"/>';
            }else if(d.isJob == '1'){
                return '<input name="'+d.realName+'" value="'+d.id+'" id="isJobTpl" type="checkbox" lay-skin="switch" lay-text="在职|离职" lay-filter="isJobTpl"/>';
            }else{
                return "";
            }
        }
    },
    {field:'wealth', title: '操作',fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
]];
var tableIns, pageCurr;
layui.use(['table', 'form'], function() {
    var table = layui.table;
    var form = layui.form;
    // 初始化表格
    tableIns = table.render({
        elem: '#viewGrid',
        url: contextPath + "/user/queryUserList.action",
        method:'post',
        where:{},
        cols: cols,
        page: {
            limit:10,
            limits:[10, 100, 150, 200]
        },
        id : 'viewGrid',
        done: function(res, curr, count){
            pageCurr = curr;
        }
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
        },
        // 搜索
        btnSearch : function () {
            // 监听表单提交
            form.on('submit(btnSearch)', function(data){
                //重新加载table
                selfFuns.load(data);
                return false;
            });
        }
    };

    // 监听在职操作
    form.on('switch(isJobTpl)', function(obj){
        selfFuns.setJobUser(obj, this.value, this.name, obj.elem.checked);
    });

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
            var currentUserId = $("#currentUserId").html();
            var id = selectData.id;
            if(currentUserId == id){
                layer.alert("不能删除自己")
                return;
            }
            // 删除用户
            layer.confirm('是否删除该用户!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnData = commonFuns.$Ajax(contextPath + "/user/deleteUser.action", {"id" : id});
                    commonFuns.dealResult(returnData);
                }
            );
        }
    });

});


var selfFuns = {
    // 设置用户是否离职
    setJobUser : function(obj, id, name, checked){
        var userIsJob = checked ? "在职":"离职";
        var isJob = checked ? '0' : '1';
        layer.confirm('您确定要把用户'+name+'设置为'+userIsJob+'状态吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            var returnData = commonFuns.$Ajax(contextPath + "/user/setJobUser.action", {"id" : id, "isJob" : isJob});
            commonFuns.dealResult(returnData);
            // 加载load方法
            selfFuns.load(obj);
        }, function(){
            // 加载load方法
            selfFuns.load(obj);
        });
    },
    //重新加载table
    load : function(obj){
        tableIns.reload({
            where: obj.field
            , page: {
                curr: pageCurr //从当前页码开始
            }
        });
    }
};