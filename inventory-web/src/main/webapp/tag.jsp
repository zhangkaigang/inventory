<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page import="com.inventory.vo.UserVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.inventory.vo.PermissionVO" %>
<%@ page import="net.sf.json.JSONArray" %>
<%
    UserVO userVO = (UserVO) request.getSession().getAttribute("sessionUser");
    List<PermissionVO> menuList = userVO.getMenuList();
    JSONArray menuListArray = JSONArray.fromObject(menuList);
%>
<script type="text/javascript">
    var menuList = <%= menuListArray%>;
</script>