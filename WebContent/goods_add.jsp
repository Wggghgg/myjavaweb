<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="goodsservlet?method=add" method="post">
				<input type="hidden" name="id" value="${requestScope.goods.id}">
				<input type="hidden" name="pubdate" value="${requestScope.goods.pubdate}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>销量</td>
						<td>库存</td>
						<td>种类</td>
						<td>介绍</td>
						<td>图片</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" /></td>
						<td><input name="price" type="text" /></td>
						<td><input name="sales" type="text" /></td>
						<td><input name="stock" type="text"/></td>
						<td><input name="type" type="text" /></td>
						<td><input name="intro" type="text" /></td>
						<td><input name="picture" type="text" /></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
					
				</table>
			</form>
</body>
</html>