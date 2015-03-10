<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.wagentim.homeapps.auth.*,
    			cn.wagentim.homeapps.entities.managers.*,
    			cn.wagentim.homeapps.utils.*,
				cn.wagentim.homeapps.entities.*,
				java.util.List,
				com.google.appengine.labs.repackaged.org.json.*,
				java.net.URLEncoder,
				javax.servlet.http.Cookie,
				java.io.PrintWriter
    			" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="style/page.css">
<link rel="stylesheet" href="style/order.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="/js/order.js"></script>
<title>代购管理系统</title>
	<%
		if( !Auth.isSessionAvailable(request) )
		{
			response.sendRedirect(Constants.PAGE_LOGIN);
		}
	%>
</head>

<body>
	<%
			String json_customers = Utils.toJson(DataManager.INSTANE.DB_DATA().getAllEntity(CustomerEntity.class));
	%>

	<div id="command_list" class="container">
		<button id="add_order" type="button" class="btn btn-warning"
			role="button">添加订单</button>
		<button id="modify_customer" type="button" class="btn btn-success"
			role="button">编辑客户</button>
		<hr />
	</div>
	<div id='order_list' class='container'></div>
	<script type="text/javascript">
		window.json_customer = <%=json_customers %>;
	</script>
</body>
</html>