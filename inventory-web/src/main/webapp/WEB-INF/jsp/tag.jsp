<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page import="com.inventory.entity.UserVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.inventory.entity.PermissionVO" %>
<%@ page import="net.sf.json.JSONArray" %>
<%
    UserVO userVO = (UserVO) request.getSession().getAttribute("sessionUser");
    List<PermissionVO> menuList = userVO.getMenuList();
    JSONArray menuListArray = JSONArray.fromObject(menuList);
%>