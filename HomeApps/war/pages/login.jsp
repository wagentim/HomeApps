<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.wagentim.homeapps.auth.*, cn.wagentim.homeapps.entities.managers.*, cn.wagentim.homeapps.utils.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/style/page.css">
<link rel="stylesheet" href="/style/login.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="/js/login.js"></script>
<title>家用应用服务 - 用户登录</title>
</head>
<body>
<%
	final String TAG_USER_NAME = Constants.USER_NAME;
	final String TAG_PASSWORD = Constants.PASSWORD;

	final String userName = request.getParameter(TAG_USER_NAME);
	final String password = request.getParameter(TAG_PASSWORD);

	if (!Validator.isNullOrEmpty(userName) && !Validator.isNullOrEmpty(password))
	{
		String md5 = Auth.getMD5Encode(userName, password);
		DataManager.INSTANE.CACHE_DATA().addNewAuth(md5);
		HttpSession newSession = request.getSession(true);
		newSession.setAttribute(Constants.AUTH, md5);
		newSession.setMaxInactiveInterval(Auth.MAX_SESSION_TIME_OUT);
		response.sendRedirect(Constants.PAGE_EDIT_USER);
	}
%>
<div class="login_block">
		<div class="login_title">
			<h2>用户登录</h2>
		</div>
		<hr />
		<form id="login_form" class="form-horizontal" role="form"
			action="#" method="POST">
			<div class="form-group">
				<div class="col-sm-1">
					<span class="glyphicon glyphicon-user"
						style="vertical-align: middle"></span>
				</div>
				<div class="col-sm-11">
					<input type="text" class="form-control" id="usr" name="<%=TAG_USER_NAME%>"
						placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-1">
					<span class="glyphicon glyphicon-wrench"
						style="vertical-align: middle"></span>
				</div>
				<div class="col-sm-11">
					<input type="password" class="form-control" id="pwd" name="<%= TAG_PASSWORD %>"
						placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<button id="login_btn" type="submit" class="btn btn-primary">登录</button>
				</div>
			</div>
		</form>
		<hr />
		<div hidden=true>
			<a href="#">忘记密码?</a>
		</div>
	</div>
</body>
</body>
</html>