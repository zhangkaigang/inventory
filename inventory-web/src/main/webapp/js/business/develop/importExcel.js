var choose_file_flag = false;
layui.use(['upload', 'form'], function(){
   var upload = layui.upload;
   var form = layui.form;
    //选完文件后不自动上传
    upload.render({
        elem : '#fileUpload',
        url : contextPath + '/excel/importExcel.action?charset=utf-8',
        auto : false,
        multiple : false,
        accept : 'file',
        bindAction : '#importData',
        field : 'fileExcel',// 若不设置则默认file
        data : {
            'fileName': function(){
                return $('#fileName').val();
            }
        },
        choose : function(obj){
            // 预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            obj.preview(function(index, file, result){
                if(file.name.length>0) {
                    choose_file_flag=true;
                }
                $("input[name='fileExcel']").parent().append("<span class='layui-inline layui-upload-choose'>"+file.name+"</span>");
                $("#fileName").val(file.name);
            })
        },
        before : function (obj) {
            var fileName = $("#fileName").val();
            if (!fileName) {
                layer.alert("请上传附件");
                return false;
            }
            var subFix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (subFix.toLocaleUpperCase() == "XLS" || subFix.toLocaleUpperCase() == "XLSX") {
                // 做提交前的操作，比如设置默认值啥的

            } else {
                layer.alert("请上传Excel附件");
                return false;
            }
        },
        done : function(res){
            console.log(res);
            if(res.result == "SUCCESS"){
                layer.msg('导入成功');
                setTimeout(function () {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }, 2000);
            }else{
                layer.alert('导入失败，请点击查看导入日志');
                $("#viewImpInfo").removeClass("layui-hide");
                $("#errorExport").removeClass("layui-hide");
            }
        }
    });

    commonFuns.renderForm();

    // 导入按钮，判断是否有选择文件
    $('#importData').click(function(){
        if(choose_file_flag == false){
            layer.alert("请上传Excel附件");
            return false;
        }
    });

    // 绑定取消按钮事件
    $(document).on('click','#btnCancel',function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        return false;
    });
});

