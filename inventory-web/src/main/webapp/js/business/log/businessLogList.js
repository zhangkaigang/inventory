var cols = [[
    {field:'logName', title: '日志名称'},
    {field:'operateName', title: '操作人'},
    {field:'className', title: '类名'},
    {field:'methodName', title: '方法名'},
    {field:'createDate', title: '时间'},
    {field:'message', title: '具体消息'},
    {field:'wealth', title: '操作',fixed: 'right', width:120, align:'center', toolbar: '#barDemo'}
]];
var tableIns, pageCurr;
layui.use(['table', 'form', 'laydate'], function() {
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;
    tableIns = table.render({
        elem: '#viewGrid',
        url: contextPath + "/log/queryBusinessLogList.action",
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
                    var returnData = commonFuns.$Ajax(contextPath + "/log/clearBusinessLog.action");
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

    // 监听行工具事件
    table.on('tool(viewGrid)', function(obj) {
        var selectData = obj.data;
        if (obj.event === 'btnView') {
            var returnData = commonFuns.$Ajax(contextPath+"/log/queryBusinessLogDetailById.action", {"id" : selectData.id});
            if(returnData.result == "SUCCESS"){
                var data = returnData.data;
                var detail = data.detail == "" ? data.message : data.detail;
                // 查看详情
                layer.open({
                    title: "查看日志详情",
                    type: 1,
                    area: ['650px', '400px'], //宽高
                    content: '<div style="padding: 20px;">'+detail+'</div>'
                });
            }

        }
    });
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