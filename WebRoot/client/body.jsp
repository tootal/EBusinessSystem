<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>首页体</title>
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
  </head>
  
  <body style="text-align:center;margin-top: 10px;">
    <div id="content" style="margin:0 auto;width:840px;">
    	<div id="category" style="float:left; width:200px; border:1px solid red; text-align:left; height:300px;"> 
    		分类列表：
    		<ul>		
	    		<c:forEach var="category" items="${categories }">
	    			<li>
	    				<a href="${pageContext.request.contextPath }/client/IndexServlet?method=listClothesWithCategory&categoryId=${category.id}">${category.name }</a>
	    			</li>
	    		</c:forEach>
    		</ul>	
    	</div>
    	<div id="clothesandpage" style="float:left; margin-left:30px;">   		
    		<div id="clothes">
    			<c:forEach var="clothes" items="${page.list }">
    				<div id="clothes" style="height:150; margin-top:20px;">
    					<div id="image" style="float:left;">
    						<img src="${pageContext.request.contextPath }/images/${clothes.image}" height=150 width=100>
    					</div>
    					<div id="bookInfo" style="float:left; text-align:left;">
    						<ul>
    							<li>${clothes.name }</li>
    							<li>品牌：${clothes.brand }</li>
    							<li>售价：${clothes.price }</li>
    							<li>
    								<a href="${pageContext.request.contextPath }/client/BuyServlet?clothesId=${clothes.id}">加入购物车</a>
    							</li>
    						</ul>
    					</div>
    				</div>
    				<div style="clear:both"></div><!-- 做div浮动后，为了不影响后续页面的显示，在这里清楚浮动效果 -->
    			</c:forEach>
    		</div>
    		<div style="clear:both"></div><!-- 做div浮动后，为了不影响后续页面的显示，在这里清楚浮动效果 -->
    		<div id="page" style="margin-top:20px; text-align:center;">
    			当前第[${page.pageNum }]页 &nbsp;&nbsp;
			    <c:forEach var="pageNum" begin="${page.startPage }" end="${page.endPage }">
			    	[<a href="${pageContext.request.contextPath }/client/IndexServlet?method=${param.method }&pageNum=${pageNum}&categoryId=${param.categoryId}">${pageNum }</a>]
			    </c:forEach>
			    &nbsp;&nbsp;
			    总共[${page.totalPage }]页，共[${page.totalRecord }]条记录
    		</div>
    	</div>
    </div>
    <script src="../bootstrap/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
