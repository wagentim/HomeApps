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
<title>管理订单</title>
	<%
		if( !Auth.isSessionAvailable(request) )
		{
			response.sendRedirect(Constants.PAGE_LOGIN);
		}
		
		Long userID = DataManager.INSTANE.CACHE_DATA().getUserID(Auth.getSession(request));
	%>
</head>

<body>
	<%
	    String json_customers = JSONUtils.toJsonString(DataManager.INSTANE.DB_DATA().getAllEntity(CustomerEntity.class));
					String json_products = JSONUtils.toJsonString(DataManager.INSTANE.DB_DATA().getAllEntity(ProductEntity.class));
	%>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/pages/home.jsp">HomeApps</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="order.jsp">管理订单 <span class="sr-only">(current)</span></a></li>
					<li><a href="usereditor.jsp">编辑客户<span class="sr-only">(current)</span></a></li>
					<li><a href="producteditor.jsp">编辑商品<span class="sr-only">(current)</span></a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="搜索客户名字">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div id="command_list" class="container">
		<button id="add_order" type="button" class="btn btn-warning"
			role="button">添加订单</button>
		<button id="remove_all_order" type="button" class="btn btn-warning"
			role="button">Remove Orders</button>
	</div>
	<div id='order_list' class='container'></div>
	<script type="text/javascript">
		window.json_customer = <%=json_customers %>;
		window.json_product = <%=json_products %>;
		window.userID = <%=userID %>
	</script>
	
	
</body>
</html>