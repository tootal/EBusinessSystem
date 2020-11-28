<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>统计报表</title>
    <link href="../css/table.css" rel="stylesheet" type="text/css"/>
  </head>
  
  <body style="text-align:center;">
    <h2>统计报表</h2>
     <c:set value="0" var="sumPrice"/>
    <table width="60%" border="1" align="center" style="text-align: center;" id="tab">
    	<tr>
    		<th>商品名称</th>
    		<th>销售数量</th>
    		<th>销售总价</th>
    	</tr>
    	<c:forEach var="statistic" items="${statistics }">
    		<tr>
    			<td>${statistic.name }</td>
	    		<td>${statistic.squantity}</td>
	    		<td>${statistic.sprice }</td>
    		</tr>
    		 <c:set value="${sumPrice + statistic.sprice}" var="sumPrice"/>
    	</c:forEach>
	</table>
	<h4 style="text-align:center;" id="h4">全部商品销售总价：${sumPrice} </h4>
  </body>
</html>
