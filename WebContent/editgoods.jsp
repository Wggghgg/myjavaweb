<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="w.bean.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品管理</title>
	<!--  %@include file="head.jsp"%-->
	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {
				return confirm("确认删除【"+$(this).parent().parent().find("td:first").text()+"】？吗");
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<!--  img class="logo_img" alt="" src="../../static/img/logo.gif" -->
			<span class="wel_word">商品管理系统</span>

	</div>
	
	<div id="main">
		<table>
			<td><a href="goods_add.jsp">添加商品</a></td>
			<tr>
				<td>id</td>
				<td>名称</td>
				<td>价格</td>
				<td>种类</td>
				<td>销量</td>
				<td>库存</td>
				<td>介绍</td>
				<td>图片</td>
				<td colspan="2">操作</td>
			</tr>
			
       
			<c:forEach items="${goods}" var="good">
			<tr>
				<td>${good.id }</td>
				<td>${good.name}</td>
				<td>${good.price}</td>
				<td>${good.type}</td>
				<td>${good.sales}</td>
				<td>${good.stock}</td>
				<td>${good.intro}</td>
				<td>${good.picture}</td>
				<td><a href="goodsservlet?method=getGoods&id=${good.id}">修改</a></td>
				<td><a href="goodsservlet?method=delete&id=${good.id}">删除</a></td>
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


		

	</div>
</body>
</html>