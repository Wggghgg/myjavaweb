<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单预览页面</title>
<link rel="stylesheet" type="text/css" href="css/login2.css">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	function ordersubmit(){
			alert(document.getElementById("address").value);
			location.href = "orderservlet?method=addOrder&address="+document.getElementById("address").value;
		}
</script>
</head>
<body style="background-color:#f5f5f5">
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>订单预览</h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-striped table-hover">
 				<tr>
 					<th>序号</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 					
 				</tr>
 				<c:set value="0" var="sum"></c:set>
 				<c:forEach items="${cartItems}" var="c" varStatus="i">
	 				<tr>
	 					<th>${i.count}</th>
	 					<th>${c.goods.name}</th>
	 					<th>${c.goods.price}</th>
	 					<th>${c.num}</th>
	 					<th>${c.money}</th>
	 				</tr>
	 				<c:set var="sum" value="${sum+c.money}"></c:set>
 				</c:forEach>
 				<tr>
 				 <td colspan="5">
 				 	<h5>收货地址</h5>
 				 	<input id="address" name="address" type="text">
 				 </td>
 				</tr>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<div style="margin-left: 40px;">	  
	            <h4>商品金额总计：<span id="total" class="text-danger"><b>￥&nbsp;&nbsp;${sum}</b></span></h4>
		</div>
	</div>
	<div class="row pull-right" style="margin-right: 40px;">
		 <div style="margin-bottom: 20px;">
	            <button  id="btn_add" onclick="ordersubmit()">提交订单</button>
	     </div>
	</div>
</div>
	
    
<!-- 底部 -->

</body>
</html>