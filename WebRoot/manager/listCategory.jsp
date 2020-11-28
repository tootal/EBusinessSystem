<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>列出所有分类</title>
    <link href="../css/table.css" rel="stylesheet" type="text/css"/>
  </head>
  
  <body style="text-align:center;">
  	<h2>分类信息</h2>
  	<table border="1" width="50%" align="center">
  		<tr style="text-align:center">
  			<th>分类名称</th>
  			<th>分类描述</th>
  		</tr>
	    <c:forEach var="c" items="${categories }">
	    	<tr style="text-align:center">
	  			<td>${c.name }</td>
	  			<td>${c.description }</td>
  			</tr>
	    </c:forEach>
  	</table>
  </body>
</html>
