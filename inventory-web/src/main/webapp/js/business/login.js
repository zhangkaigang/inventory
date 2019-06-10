function init(){
    // 取得错误信息值
    var msg = $("#errorMsg").val();
    if(msg){
       layer.alert(msg);
    }
}

// 刷新验证码
function codeFresh(){
    document.getElementById("imgCode").src = contextPath + "/validateCode.jsp?"+new Date().getTime();
}

layui.use(['form'], function() {
    var form = layui.form;
    commonFuns.renderForm();
    // 监听表单提交-登录
    form.on("submit(login)",function () {
        var flag = selfFuns.checkParams();
        if(flag) {
            $("#loginForm").submit();
        }
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