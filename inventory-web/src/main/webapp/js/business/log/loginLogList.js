var cols = [[
    {field:'logName', title: '日志名称'},
    {field:'loginName', title: '用户'},
    {field:'createDate', title: '时间'},
    {field:'ip', title: '登录IP'}
]];
var tableIns, pageCurr;
layui.use(['table', 'form', 'laydate'], function() {
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;

    tableIns = table.render({
        elem: '#viewGrid',
        url: contextPath + "/log/queryLoginLogList.action",
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

    // 渲染表单
    commonFuns.renderForm();

    // 日期
    laydate.render({
        elem: '#createDateStart',
        type: 'datetime',
        trigger: 'click'
    });
    laydate.render({
        elem: '#createDateEnd',
        type: 'datetime',
        trigger: 'click'
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 清空日志
        btnClear : function(){
            layer.confirm('是否清空日志，清空后无法恢复!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnData = commonFuns.$Ajax(contextPath + "/log/clearLoginLog.action");
                    commonFuns.dealResult(returnData);
                }
            );
        },
        // 搜索
        btnSearch : function(){
            // 监听表单提交
            form.on('submit(btnSearch)', function(data){
                //重新加载table
                selfFuns.load(data);
                return false;
            });
        }
    };
});

var selfFuns = {
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