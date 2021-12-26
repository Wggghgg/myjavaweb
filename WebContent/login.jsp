<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
</head>
<body>
		<!-- login -->
		<div class="top center">
			<div class="logo center">
				<a href="${pageContext.request.contextPath }/index.jsp" target="_blank"></a>
			</div>
		</div>
		<form  method="post" action="userservlet?method=login" class="form center" id="userLogin" >
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">登录</div>
					<div class="right fr">您还没有账号？<a href="${pageContext.request.contextPath }/register.jsp" target="_self">立即注册</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="login_main center">
					<div class="username">
						<div class="left fl">用户名:&nbsp;</div>
						<div class="right fl">
						<input class="shurukuang" type="text" name="username" id="username"  placeholder="请输入你的用户名"/>
						<label id="nameMsg"></label>
						</div>
					</div>
					<div class="username">
						<div class="left fl">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</div>
						<div class="right fl">
						<input class="shurukuang" type="password" name="password"  id="pwd"  placeholder="请输入你的密码"/>	
						</div>
					</div>
					<div class="username">
						<div class="left fl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="right fl"><label id="checkMsg"></label></div>
					</div>
					<div class="username">
						<input id="autoLogin" name="auto" type="checkbox" />&nbsp;&nbsp;两周以内自动登录
						<span id="autoLoginMsg"></span>
					</div>
					<div class="login_submit">
						<input class="submit" type="submit" name="submit" value="立即登录" id="btn" >
					</div>
					<span style="color:#ff0000">${msg}</span>
				</div>	
			</div>
		</div>
		</form>
		<footer>
			<div class="copyright">简体 | 繁体 | English | 常见问题</div>
		
		</footer>
	</body>
</html>