<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="goodsservlet?method=update" method="post">
	<table>
					<td><input name="id" type="hidden" value="${requestScope.goods.id}"/></td>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>销量</td>
						<td>库存</td>
						<td>种类</td>
						<td>介绍</td>
						<td>图片</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.goods.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.goods.price}"/></td>
						<td><input name="sales" type="text" value="${requestScope.goods.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.goods.stock}"/></td>
						<td><input name="type" type="text" value="${requestScope.goods.type}"/></td>
						<td><input name="intro" type="text" value="${requestScope.goods.intro}"/></td>
						<td><input name="picture" type="text" value="${requestScope.goods.picture}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
		</table>
</form>
</body>
</html>