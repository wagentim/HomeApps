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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet"
		href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="/style/page.css">
	<link rel="stylesheet" href="/style/edit.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="/js/jquery.cookie.js"></script>
		<script src="/js/editproduct.js"></script>
	<title>编辑商品</title>
	<%
		if( !Auth.isSessionAvailable(request) )
		{
			response.sendRedirect(Constants.PAGE_LOGIN);
		}
	%>
</head>
<body>
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
				<a class="navbar-brand" href="#">HomeApps</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="order.jsp">代购管理系统 <span class="sr-only">(current)</span></a></li>
					<li><a href="usereditor.jsp">编辑客户<span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="#">编辑商品<span class="sr-only">(current)</span></a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="搜索商品名称">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container main">
		<div class="list-group col-xs-offset-1 col-xs-2">
			<a href='#' class='list-group-item active new' style='text-align: center'>+ 新建商品</a>
		<%
			String json_products = Utils.toJson(DataManager.INSTANE.DB_DATA().getAllEntity(ProductEntity.class));

			if( !Validator.isNullOrEmpty(json_products) )
			{
				JSONArray products = Utils.fromJson(json_products);

				if( null != products && products.length() > 0 )
				{
					for(int i = 0; i < products.length(); i++)
					{
						final JSONObject customer = products.getJSONObject(i);
						if( null != customer )
						{
						    long id = customer.getLong("id");
						    String name = customer.getString("name");
		%>
							<a href='#' class='list-group-item item' style='text-align: center' onclick="" uid='<%= id %>'><%=name %></a>
						<%
						}
					}
				}
			}

		%>
		</div>
		<div class="container col-xs-offset-1 col-xs-8 edit">
			<form class="form-horizontal myform" role="form" action="/data?entity=1&opt=0" method="POST">
				<div class='form-group'>
					<div class="col-sm-2">
						<label for='uid' class='control-label'
							style='vertical-align: middle'>商品ID: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="uid" name="uid"
							value="0" readonly>
					</div>
				</div>
				<div class='form-group'>
					<div class="col-sm-2">
						<label for='name' class='control-label'
							style='vertical-align: middle'>商品名称: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name"
							name="name" placeholder="商品名称" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='categorie' class='control-label'
							style='vertical-align: middle'>所属目录: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="categorie" name="categorie"
							placeholder="所属目录" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='defaultamount' class='control-label'
							style='vertical-align: middle'>默认数量: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="defaultamount" name="defaultamount"
							placeholder="默认数量" value="" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='defaultprice' class='control-label'
							style='vertical-align: middle'>默认价格: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="defaultprice"
							name="defaultprice" placeholder="默认价格" value="">
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-sm-2">
						<button type="button" class="btn btn-danger delete">删除</button>
					</div>
					<div class="col-sm-offset-8 col-sm-2 save">
						<button type=submit class="btn btn-success save">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		window.json_product = <%=json_products %>;
	</script>

</body>
</html>