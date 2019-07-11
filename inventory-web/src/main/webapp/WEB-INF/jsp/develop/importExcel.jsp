<%--
  Created by IntelliJ IDEA.
  User: zkaigang
  Date: 2019/7/4
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/tag.jsp"%><html>
<head>
    <%@include file="/commonHead.jsp"%>
</head>
<body class="layui-layout-body">


    <div class="layui-layout layui-layout-admin">
        <!--body主体-->
        <form id="fileForm" method="post" enctype="multipart/form-data">
            <input type="hidden" name="fileName" id="fileName"/>
            <div  class="layer_self_wrap" style="margin: 1%">
                <div class="layui-form-item">
                    <label class="layui-form-label">文件：</label>
                    <div class="layui-upload">
                        <button type="button"
                                class="layui-btn layui-btn-normal layui-btn-sm"
                                id="fileUpload"><i class="layui-icon">&#xe67c;</i>选择文件</button>
                    </div>
                </div>
            </div>
        </form>
            <div class="layui-form-item">
                <div class="layui-input-block" style="margin-left: 10px;">
                    <button class="layui-btn  layui-btn-sm" id="importData" >导入</button>
                    <button class="layui-btn  layui-btn-sm layui-hide" onclick="viewImpInfo();" id="viewImpInfo" >查看导入日志</button>
                    <button class="layui-btn  layui-btn-sm layui-hide" onclick="errorExport();" id="errorExport" >导出错误数据</button>
                    <button class="layui-btn  layui-btn-sm" id="btnCancel">取消</button>
                </div>
            </div>
        </div>
    <!--body尾-->
    <%@include file="/commonBottom.jsp"%>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/develop/importExcel.js"></script>
</body>
</html>
