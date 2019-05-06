var cols = [[
    {field:'permissionName', title: '权限名称'},
    {field:'itemType', title: '权限分类'},
    {field:'permissionUrl', title: '权限路径'},
    {field:'permissionCode', title: '权限编码'},
    {field:'sort', title: '顺序'},
    {width:180,title: '操作', align:'center', templet: function(d){
            var html='';
            var editBtn = '<a class="layui-btn layui-btn-xs" lay-event="btnEdit">编辑</a>';
            var addChildBtn = '<a class="layui-btn layui-btn-xs" lay-event="btnAddChild">添加子节点</a>';
            var deleteBtn = '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="btnDelete">删除</a>';
        return editBtn + addChildBtn + deleteBtn;
        }
    }
]];

layui.config({
    base: contextPath + '/js/layui/extend/treegrid/'
}).extend({
    treeGrid:'treeGrid'
}).use(['treeGrid'], function(){
    var treeGrid = layui.treeGrid;
    treeGrid.render({
        id:'viewGrid'
        ,elem: '#viewGrid'
        ,url:contextPath + "/permission/queryPermissionList.action"
        ,cellMinWidth: 100
        ,idField:'id'//必須字段
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'parentId'//树形父id字段名称
        ,treeShowName:'permissionName'//以树形式显示的字段
        ,heightRemove:[".dHead",10]//不计算的高度,表格设定的是固定高度，此项不生效
        ,height:'100%'
        ,isFilter:false
        ,iconOpen:false//是否显示图标【默认显示】
        ,isOpenDefault:true//节点默认是展开还是折叠【默认展开】
        ,loading:true
        ,method:'post'
        ,isPage:false
        ,cols: cols
        ,parseData:function (res) {//数据加载后回调
            return res;
        }
        ,onClickRow:function (index, o) {
            // console.log(index,o,"单击！");
        }
        ,onDblClickRow:function (index, o) {
            // console.log(index,o,"双击");
        }
        ,onCheck:function (obj,checked,isAll) {
            // console.log(obj,checked,isAll,"复选");
        }
        ,onRadio:function (obj) {
            // console.log(obj,"单选");
        }
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 自定义函数
    var active = {
        // 开通权限(新增)
        btnAdd : function(){
            var url = contextPath + "/permission/addPermissionPage.action"
            param = {
                parentId : 0
            };
            // 页面层
            layer.open({
                type: 2,
                title : '开通权限',
                area: ['500px', '600px'],
                content: url
            });
        }
    };

    // 监听行工具事件
    treeGrid.on('tool(viewGrid)', function(obj){
        var selectData = obj.data;
        if(obj.event === 'btnAddChild'){
            console.log(selectData);
            // 添加子节点权限
            var url = contextPath + "/permission/addPermissionPage.action"
            param = {
                parentId : selectData.id
            };
            // 页面层
            layer.open({
                type: 2,
                title : '开通权限',
                area: ['500px', '600px'],
                content: url
            });
        }
    });
});