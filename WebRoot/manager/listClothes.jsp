<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>显示所有商品</title>
    <link href="../css/table.css" rel="stylesheet" type="text/css"/>
  </head>
  
  <body style="text-align:center;">
    <h2>商品信息</h2>
    <table border="1" width="60%" align="center">
    	<tr style="text-align:center">
  			<th>商品名称</th>
  			<th>品牌</th>
  			<th>价格</th>
  			<th>图片</th>
  			<th>描述</th>
  			<th>修改</th>
  			<th>删除</th>
  		</tr>
  		<c:forEach var="clothes" items="${page.list }">
  			<tr style="text-align:center">
  				<td>${clothes.name }</td>
	  			<td>${clothes.brand }</td>
	  			<td>${clothes.price }</td>
	  			<td>
					<img src="${pageContext.request.contextPath }/images/${clothes.image}" height="50px;"width="50px;""></img>
				</td>
	  			<td>${clothes.description }</td>
	  			<td><a href="${pageContext.request.contextPath }/manager/urlDecodeServlet?id=${clothes.id}&name=${clothes.name}&brand=${clothes.brand}&price=${clothes.price}&image=${clothes.image}&description=${clothes.description}">修改</a></td>
	  			<td><a href="${pageContext.request.contextPath }/manager/ClothesServlet?method=delete&id=${clothes.id}">删除</a></td>
  			</tr>
  		</c:forEach>
    </table>
    <br>
    当前第[${page.pageNum }]页 &nbsp;&nbsp;
    <c:forEach var="pageNum" begin="${page.startPage }" end="${page.endPage }">
    	[<a href="${pageContext.request.contextPath }/manager/ClothesServlet?method=list&pageNum=${pageNum}">${pageNum }</a>]
    </c:forEach>
    &nbsp;&nbsp;
    总共[${page.totalPage }]页，共[${page.totalRecord }]条记录
  </body>
</html>
