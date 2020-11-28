<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理员登录</title>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body style="text-align: center">
<br/><br/>
<h1>网上商城平台</h1>
<h3>管理员登录入口</h3>
<br/><br/>
<div style="width:200px;height:300px;margin: 0 auto;">
	<form class="form"
		action="${pageContext.request.contextPath }/manager/AdminLoginServlet"
		method="post">
		<div class="form-group">
			<input class="form-control" placeholder="管理员名" type="text"
				name="adminName">
		</div>
		<div class="form-group">
			<input class="form-control" type="password" placeholder="密码"
				name="password">
		</div>
		<input class="btn btn-default" type="submit" value="登陆">
	</form>
	</div>
	<script src="../bootstrap/jquery.min.js"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
