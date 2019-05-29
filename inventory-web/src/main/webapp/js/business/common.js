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
    },
    // 处理结果
    dealResult : function(returnData){
        if(returnData.result == "SUCCESS"){
            layer.alert("操作成功！", function(){
                // 刷新页面
                window.location.reload();
            });
        }else{
            layer.alert("操作失败");
            return false;
        }
    },
    // 处理子页面的结果
    dealChildPageResult : function(returnData){
        if(returnData.result == "SUCCESS"){
            layer.alert("操作成功！", function(){
                // 刷新父页面
                window.parent.location.reload();
                var index = parent.layer.getFrameIndex(window.name);
                // 关闭弹出层
                parent.layer.close(index);
            });
        }else{
            layer.alert("操作失败");
            return false;
        }
    },

    btnCancel : function(){
        // 获取当前弹出层的等级
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
};