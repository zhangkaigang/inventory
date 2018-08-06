<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common.jspf"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新家福进销存系统</title>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">数据表</a></li>
        <li><a href="#">基本内容</a></li>
    </ul>
</div>

<div class="rightinfo">

    <div class="tools">

        <ul class="toolbar">
            <li class="clickUI"><span><img src="images/t01.png" /></span>添加</li>
            <li class="click"><span><img src="images/t02.png" /></span>修改</li>
            <li class="click"><span><img src="images/t03.png" /></span>删除</li>
            <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>


        <ul class="toolbar1">
            <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>

    </div>


    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>登录名</th>
            <th>姓名</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="c" varStatus="st">
                <tr>
                    <td>${c.loginName}</td>
                    <td>${c.name}</td>
                    <td>${c.createDate}</td>
                    <td>${c.deleteState}</td>
                    <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink"> 删除</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="javascript:;">1</a></li>
            <li class="paginItem current"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>


    <%--添加、修改--%>
    <div class="tipUI">
        <div class="tiptopUI"><span>添加用户</span><a></a></div>

        <div class="tipinfoUI">
            <span><img src="images/ticon.png" /></span>
            <div class="tiprightUI">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtnUI">
            <input name="" type="button"  class="sureUI" value="确定" />&nbsp;
            <input name="" type="button"  class="cancelUI" value="取消" />
        </div>
    </div>

    <%--删除--%>
    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>
        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" />&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>




</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>


</body>
</html>
