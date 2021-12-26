<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户注册</title>

</head>
<body class="main">
<%@include file="head.jsp" %>
<%@include file="menu_search.jsp" %>
<div id="divcontent">
	<form id="regist_form" action="userservlet?method=register"
		method="post" onsubmit="return checkForm();">
		<table width="850px" border="0" cellspacing="0">
			<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="username" name="username" class="form-control col-sm-10"
								placeholder="Username" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="usernameMsg" class="help-block "></span>
							<lable class="error" for="username"></lable>
						</p>
						</div>
					</div>
					<label class="col-sm-2 control-label">昵称</label>
					<div class="col-sm-8" style="width: 40%">
							<input type="text" id="nickame" name="nickname" class="form-control col-sm-10"
								placeholder="Nickname" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="nicknameMsg" class="help-block "></span>
							<lable class="error" for="nickname"></lable>
						</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" name="password" id="password"
								class="form-control col-sm-10" placeholder="Password"  />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="passwordMsg" class="help-block ">请输入6位以上字符</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" name="repassword" class="form-control col-sm-10"
								placeholder="Password Again" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="repasswordMsg" class="help-block ">两次密码要输入一致哦</span></p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="email" name="email" class="form-control col-sm-10"
								placeholder="Email" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="emailMsg" class="help-block ">填写正确邮箱格式</span></p>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-7 col-sm-push-2">
							<input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg" style="width: 200px;"/> &nbsp; &nbsp;
							<input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
						</div>
					</div>
					<div class="text-center" style="color:red">${registerMsg}</div>
			 </table></form>
</div>
</body>
</html>