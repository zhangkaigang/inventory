// 封装通用的函数
var commonFuns = {
    // 重新渲染表单
    renderForm : function(){
        layui.use('form', function(){
            // 高版本建议把括号去掉，有的低版本，需要加()
            var form = layui.form;
            // 也可以单单初始化下拉框form.render('select');
            form.render();
        });
    },
    // 封装ajax异步调用
    $Ajax : function(url,data){
        var returnData;
        $.ajax({
            type : 'post',
            url : url,
            async : false,
            data : data,
            dataType : 'json',
            success : function(data){
                returnData = data;
            }
        });
        return returnData;
    }
};