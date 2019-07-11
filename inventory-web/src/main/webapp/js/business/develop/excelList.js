var cols = [[
    {field:'name', title: '客户名称'},
    {field:'contactMan', title: '联系人'},
    {field:'contactTel', title: '联系电话'},
    {field:'email', title: '邮箱'},
    {field:'address', title: '地址'},
    {field:'note', title: '备注'},
    {field:'createDate', title: '添加时间'}
]];
var tableIns, pageCurr;
layui.use('table', function() {
    var table = layui.table;
    // 初始化表格
    tableIns = table.render({
        elem: '#viewGrid',
        url: contextPath + "/customer/queryCustomerList.action",
        method: 'post',
        where: {},
        cols: cols,
        page: false,
        id: 'viewGrid',
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
        btnImport : function(){
            layer.open({
                type: 2,
                area: ['400px', '400px'],
                fixed: false, //不固定
                maxmin: true,
                content: contextPath + "/excel/importExcelPage.action"
            });
        }
    };
});