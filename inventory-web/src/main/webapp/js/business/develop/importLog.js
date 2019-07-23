layui.use(['table'], function(){
    var table = layui.table;
    // 执行渲染
    table.render({
        elem: '#viewGrid', //指定原始表格元素选择器（推荐id选择器）
        url: contextPath + "/excel/getErrorInfoList.action",
        height: 315, //容器高度
        cols:[ [
            {field: 'no', title: '错误类型', width:100},
            {field: 'errorMsg', title: '错误信息', width:380}]] //设置表头
    });
});