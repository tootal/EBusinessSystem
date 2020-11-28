<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>购物车显示列表</title>
    <link href="../css/table.css" rel="stylesheet" type="text/css"/>
  </head>
  
  <body style="text-align:center;">
  	<c:if test="${user == null }">
  		对不起，请先登录
  	</c:if>
  	<c:if test="${user != null }">
  	<h2>购物车列表</h2>
    <table width="40%" border="1" align="center" style="text-align: center;">
    	<tr>
    		<th>商品名</th>
    		<th >品牌</th>
    		<th>单价</th>
    		<th>数量</th>
    		<th>小计</th>
    	</tr>
    	<c:forEach var="me" items="${cart.map }">
    		<tr>
    			<td>${me.value.clothes.name }</td>
	    		<td>${me.value.clothes.brand }</td>
	    		<td>${me.value.clothes.price }</td>
	    		<td>${me.value.quantity }</td>
	    		<td>${me.value.price }</td>
    		</tr>
    	</c:forEach>
    	
    	<tr>
    		<th colspan="1">总价</th>
    		<td colspan="4">${cart.price }</td>
    	</tr>
	</table>
	<a href="${pageContext.request.contextPath }/client/OrderServlet">购买</a>
	</c:if>
   </body>
</html>