
layui.use(['form'], function() {
    var form = layui.form;
    // 表单监听事件-监听登录事件
    form.on("submit(btnLogin)", function(data){
        var flag = selfFuns.checkParams();
        if(flag){
            // 得到表单元素集合
            var formVal = data.field;
            var returnData = commonFuns.$Ajax(contextPath + "/login.action", formVal);
            console.log(returnData);
            if(returnData.result == "SUCCESS"){
                location.href = contextPath + "/home.action";
            } else {
                if(returnData.msg){
                    layer.alert(returnData.msg, function () {
                        location.href = contextPath + "/toLogin.action";
                    });
                }else{
                    layer.alert("登录失败", function () {
                        location.href = contextPath + "/toLogin.action";
                    });
                }
            }

        }

        /*!// 得到表单元素集合
        var formVal = data.field;
        var returnData = selfFuns.login(formVal);
        if(returnData && returnData.result == "SUCCESS"){
            layer.alert("登录成功", function(){
                location.href = contextPath+"/user/home";
            });
        }else{
            layer.alert(returnData.msg);
        }*/
        return false;
    });
});


var selfFuns = {
    // 登录表单校验
    checkParams : function(){
        var loginName = $("#loginName").val();
        var password = $("#password").val();
        var code = $("#code").val();
        if(!validateUtils.checkUserName(loginName)){
            // tips层-右
            layer.tips("用户名不能为空", '#loginName', {
                tips: [2, '#78BA32'],
                tipsMore: true
            });
            return false;
        }
        if(!validateUtils.checkPassword(password)){
            // tips层-右
            layer.tips("密码不能为空", '#password', {
                tips: [2, '#78BA32'],
                tipsMore: true
            });
            return false;
        }
        if("ok"!=validateUtils.checkPicCode(code)){
            // tips层-右
            layer.tips(validateUtils.checkPicCode(code), '#imgCode', {
                tips: [2, '#78BA32'],
                tipsMore: true
            });
            return false;
        }
        return true;
    }
};

// 校验
var validateUtils = {
    checkUserName : function(username){
        if(username == '' || $.trim(username).length == 0){
            return false;
        }
        return true;
    },
    checkPassword : function(password){
        if(password == '' || $.trim(password).length == 0){
            return false;
        }
        return true;
    },
    checkPicCode : function(code){
        if(code=='' || $.trim(code).length == 0){
            $("input[name='code']").val("");
            $("input[name='code']").focus();
            return "请您输入验证码";
        }
        if(!code.match(/\w{4}$/)){
            $("input[name='code']").val("");
            $("input[name='code']").focus();
            return 	"您输入的验证码格式有误";
        }
        return "ok";
    }
}