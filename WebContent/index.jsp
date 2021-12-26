<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="w.service.*" %>
<%@ page import="w.service.impl.*" %>
<%@ page import="java.util.List" %>
<%@ page import="w.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎光临myshoppingmall</title>
</head>
<body>
<% 
GoodsService goodsService=new GoodsServiceImpl(); 
List<Goods> goodslist = goodsService.selectAllGoods();
request.setAttribute("goods",goodslist); 
%>
	<div id="header">
			<!--img class="logo_img" alt="" src="static/img/logo.gif" -->
			<span class="wel_word">网上商城</span>
			<div>
				<c:if test="${empty sessionScope.user}">
						<a href="login.jsp">登录</a>
						
						<a href="register.jsp">注册</a> &nbsp;&nbsp;
						
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎【<span class="um_span">${sessionScope.user.username}</span>】光临本商城</span>
					
					<a href="orderList.jsp">我的订单</a>
					
					<a href="userservlet?method=logout" id="a_top" onclick="return confirm('确定要退出吗?')">注销</a>
					
					<a href="cart.jsp">购物车</a>
					
				</c:if>
				<table>
			<tr>
				<td>id</td>
				<td>名称</td>
				<td>价格</td>
				<td>种类</td>
				<td colspan="2">操作</td>
			</tr>
			
       
			<c:forEach items="${goods}" var="good">
			<tr>
				<td>${good.id }</td>
				<td>${good.name}</td>
				<td>${good.price}</td>
				<td><a href="goodsservlet?method=showGoods&id=${good.id}">查看详情</a></td>
				<td><a href="cartservlet?method=addCart&pid=${good.id}&number=1">加入购物车</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				
			</tr>	
		</table>
			<a href="manager.jsp">后台管理</a>
			</div>
	</div>
	
</body>
</html>