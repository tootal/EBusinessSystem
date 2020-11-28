<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>订单明细</title>
    <link href="../css/table.css" rel="stylesheet" type="text/css"/>
  </head>
  
  <body style="text-align:center;">
  	<h3>订单明细</h3>
    <table border="1" width="50%" align="center" style="text-align: center;">
    	<tr>
    		<th>商品名</th>
    		<th>售价</th>
    		<th>数量</th>
    		<th>应收货款</th>
    	</tr>
    	<c:forEach var="orderItem" items="${order.orderItems }">
    	<tr>
    		<td>${orderItem.clothes.name }</td>
    		<td>${orderItem.clothes.price }</td>
    		<td>${orderItem.quantity }</td>
    		<td>${orderItem.price }</td>
    	</tr>
    	</c:forEach>
    	
    	<tr>
    		<th>订单总价</th>
    		<td colspan="3">${order.price }</td>
    	</tr>
    	
    </table>
    
    <h3>收货人详细信息</h3>
    <table border="1" width="50%" align="center" style="text-align: center;">  
    	<tr>
    		<th>用户</th>
    		<th>电话</th>
    		<th>手机</th>
    		<th>地址</th>
    		<th>邮箱</th>
    	</tr>
    	<tr>
    		<td>${order.user.username }</td>
    		<td>${order.user.phone }</td>
    		<td>${order.user.cellphone }</td>
    		<td>${order.user.address }</td>
    		<td>${order.user.email }</td>
    	</tr>
	</table>
	<a href="${pageContext.request.contextPath }/manager/ConfirmOrderServlet?orderId=${order.id}&orderEmail=${order.user.email}">确认发货</a>
  </body>
</html>
