<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>管理后台</title>
  </head>
  
  <frameset rows="15%,*">
  	<frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head">
  	<c:if test="${admin==null }"> 
  	<%
  	//重定向到管理员登陆界面
  	response.sendRedirect("adminLogin.jsp");
  	%>
  	</c:if>
	
	    
	<c:if test="${admin!=null }"> 
	<frameset cols="15%,*">
  		<frame src="${pageContext.request.contextPath }/manager/left.jsp" name="left">
  		<frame src="${pageContext.request.contextPath }/manager/right.jsp" name="right">
  	</frameset>
  	</c:if>
  </frameset>
  <script src="../bootstrap/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</html>
