// 定义一些常量
var picCode;

// 初始化
function init(){
    // 初始化图片验证码
    // picCode = drawPic();
}



/*layui.use(['form' ,'layer'], function() {
    var form = layui.form, layer = layui.layer;
    // 表单监听事件-监听登录事件
    form.on("submit(login)", function(data){
        console.log('表单提交开始');
        var flag = selfFuns.checkParams();
        // if(flag != false){
            $("#loginForm").submit();
        // }
        /!*!// 得到表单元素集合
        var formVal = data.field;
        var returnData = selfFuns.login(formVal);
        if(returnData && returnData.result == "SUCCESS"){
            layer.alert("登录成功", function(){
                location.href = contextPath+"/user/home";
            });
        }else{
            layer.alert(returnData.msg);
        }*!/
        return false;
    });
});*/


var selfFuns = {
    // 登录
    login : function(formVal){
        var flag = selfFuns.checkParams();
        if(flag != false){
           return commonFuns.$Ajax(contextPath+"/user/login", formVal);
        }
    },
    // 登录表单校验
    checkParams : function(){
        var loginName = $("#loginName").val();
        var password = $("#password").val();
        var code = $("#code").val();
        if("ok" != validateUtils.checkUserName(loginName)){
            layer.alert(validateUtils.checkUserName(loginName));
            return false;
        }
        if("ok" != validateUtils.checkSimplePassword(password)){
            layer.alert(validateUtils.checkSimplePassword(password));
            return false;
        }
        if("ok"!=validateUtils.checkPicCode(code)){
            // tips层-右
            layer.tips(validateUtils.checkPicCode(code), '#canvas', {
                tips: [2, '#78BA32'],
                tipsMore: true
            });
            return false;
        }
        if(picCode.toLowerCase()!=code.toLowerCase()){
            // tips层-右
            layer.tips("请您输入正确的验证码", '#canvas', {
                tips: [2, '#78BA32'],
                tipsMore: true
            });
            return false;
        }
    }
};

// 校验
var validateUtils = {
    checkUserName : function(username){
        if(username == '' || $.trim(username).length == 0){
            return "用户名不能为空，请重新填写";
        }
        return "ok";
    },
    checkSimplePassword : function(password){
        if(password == '' || $.trim(password).length == 0){
            return "密码不能为空，请重新填写";
        }
        return "ok";
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
            return 	"您输入的验证码格式有误，请重新输入";
        }
        return "ok";
    }
}