<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>注册表单</title>
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
  
  <body style="text-align:center;">
  <div style="margin-top: 10px;">
  	<form class="form-horizontal" action="${pageContext.request.contextPath }/client/RegisterServlet" method="post">
    	<div class="form-group"><label class="col-sm-2 col-sm-offset-3 control-label">用户名：</label><div class="col-sm-3"><input class="form-control" type="text" name="username"></div></div>
    	<div class="form-group"><label class="col-sm-2 col-sm-offset-3 control-label">密码：</label><div class="col-sm-3"><input class="form-control" type="password" name="password"></div></div>
    	<div class="form-group"><label class="col-sm-2 col-sm-offset-3 control-label">电话：</label><div class="col-sm-3"><input type="text" class="form-control" name="phone"></div></div>
    	<div class="form-group"><label class="col-sm-2 col-sm-offset-3 control-label">手机：</label><div class="col-sm-3"><input type="text" class="form-control" name="cellphone"></div></div>
    	<div class="form-group"><label class="col-sm-2 col-sm-offset-3 control-label">邮箱：</label><div class="col-sm-3"><input type="text" class="form-control" name="email"></div></div>
    	<div class="form-group"><label class="col-sm-2 col-sm-offset-3 control-label">住址：</label><div class="col-sm-3"><input type="text" class="form-control" name="address"></div></div>
    	<div class="form-group"><div class="col-sm-offset-5 col-sm-3"><input class="btn btn-default" type="submit" value="注册"></div></div>
    </form>
    </div>
    <script src="../bootstrap/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
