<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>后台管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/layui/css/layui.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
    var contextPath = '<%=request.getContextPath()%>';
    // 定义
    var layer;
    layui.use(['layer'], function(){
        layer = layui.layer;
    });
    // 封装公共方法
    var commonFuns = {
        //重新渲染表单
        renderForm: function () {
            layui.use('form', function () {
                //高版本建议把括号去掉，有的低版本，需要加()
                var form = layui.form;
                form.render();
            });
        }
    }
</script>




