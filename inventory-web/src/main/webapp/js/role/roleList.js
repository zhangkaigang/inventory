var cols = [[
    {field:'roleName', title: '角色名称'},
    {field:'description', title: '角色描述'},
    {field:'roleCode', title: '角色编码'},
    {field:'createDate', title: '添加时间'},
    {field:'wealth', title: '操作',fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
]];

$(function(){
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
    });
});

