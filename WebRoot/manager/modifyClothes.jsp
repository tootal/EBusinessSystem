<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>修改商品</title>
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
  
  <body>
  <div style="margin:2% 0 0 2%;width:98%;">
  <form style="width:30%" action="${pageContext.request.contextPath }/manager/ClothesServlet?method=modify" method="post" enctype="multipart/form-data">
   	<div class="form-group">
   		<label>商品ID：</label>
    			<input class="form-control" type="text" name="id" readonly value="<%=request.getParameter("id")%>">	
   	</div>
   	<div class="form-group">
    	<label>商品名称：</label>
    			<input class="form-control" type="text" name="name" value="${param.name }">	
   	</div>
   	<div class="form-group">
    		<label>品牌：</label>
    			<input class="form-control" type="text" name="brand" value="<%=request.getParameter("brand")%>">
    	</div>
  	<div class="form-group">
    		<label>售价：</label>
    			<input class="form-control" type="text" name="price" value="<%=request.getParameter("price")%>">
   	</div>
 	<div class="form-group">
    		<label>图片：</label>
    			<img src="${pageContext.request.contextPath }/images/<%=request.getParameter("image")%>" height="50px;"width="50px;""></img> 
    			<input type="file" name="file">
   	</div>
 	<div class="form-group">
    		<label>商品描述：</label>
    			<textarea class="form-control" rows="5" cols="40" name="description"><%=request.getParameter("description")%></textarea>
   	</div>
    	
    			<input class="btn btn-default" type="reset" value="清空">
    			<input class="btn btn-default" type="submit" value="提交">
   </form>
   </div>
    <script src="../bootstrap/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
