<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<script type="text/javascript">
		function changeStatus(orderId,user){
			
			location.href="orderservlet?method=updateStatus&oid="+orderId+"&user="+user;
		}
	</script>
</head>
<body>

<%@page import="w.bean.*" %>
<%@page import="java.util.List" %>
<%@page import="w.service.*" %>
<%@page import="w.service.impl.*" %>
<%
OrderService       orderService       = new OrderServiceImpl();
User        user      = (User) request.getSession().getAttribute("user");
List<Order> orderList = orderService.selectOrderAll();
System.out.println(orderList);
request.setAttribute("orderList", orderList);
%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>订单管理系统&nbsp;&nbsp;</h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<div class="col-md-12">
			<table id="tb_list" class="table table-striped table-hover table-bordered table-condensed">
			<tr>
				<th>序号</th>
				<th>订单编号</th>
				<th>总金额</th>
				<th>订单状态</th>
				<th>订单时间</th>
				<th>收货地址</th>
			</tr>
			<c:forEach items="${orderList}" var="order" varStatus="i">
				<tr>
					<th>${i.count}</th>
					<th>${order.id}</th>
					<th>${order.money}</th>
					<th>
						<font color="red">
							<c:if test="${order.status eq 0 }">
								未支付
							</c:if>
							<c:if test="${order.status eq 1 }">
								已支付,待发货
								<button type="button" class="btn btn-danger btn-sm" onclick="changeStatus('${order.id}','${order.username}')">发货</button>
							</c:if>
							<c:if test="${order.status eq 2 }">
								已发货,待收货
							</c:if>
							<c:if test="${order.status eq 3 }">
								订单完成
							</c:if>
						</font>
					</th>
					<th>${order.time}</th>
					<th>${order.address}</th>
					<th>
					</th>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	
</div>
</body>
</html>