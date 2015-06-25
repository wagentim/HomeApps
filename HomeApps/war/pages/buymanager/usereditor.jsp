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
		<script src="/js/edituser.js"></script>
	<title>ç¼–è¾‘å®¢æˆ·</title>
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
				<a class="navbar-brand" href="/pages/home.jsp">HomeApps</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="order.jsp">Order<span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="#">User Editor<span class="sr-only">(current)</span></a></li>
					<li><a href="producteditor.jsp">Product Editor<span class="sr-only">(current)</span></a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search User">
					</div>
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container main">
		<div class="list-group col-xs-offset-1 col-xs-2">
			<a href='#' class='list-group-item active new' style='text-align: center'>+ Add New Customer</a>
		<%
		    String json_customers = JSONUtils.toJsonString(DataManager.INSTANE.DB_DATA().getAllEntity(CustomerEntity.class));

									if( !Validator.isNullOrEmpty(json_customers) )
									{
										JSONArray customers = JSONUtils.getJsonArray(json_customers);

										if( null != customers && customers.length() > 0 )
										{
											for(int i = 0; i < customers.length(); i++)
											{
												final JSONObject customer = customers.getJSONObject(i);
												if( null != customer )
												{
												    long id = customer.getLong("id");
												    String lastName = customer.getString("lastName");
												    String firstName = customer.getString("firstName");
		%>
							<a href='#' class='list-group-item item' style='text-align: center' onclick="" uid='<%= id %>'><%=lastName %> <%=firstName %></a>
						<%
						}
					}
				}
			}

		%>
		</div>
		<div class="container col-xs-offset-1 col-xs-8 edit">
			<form class="form-horizontal myform" role="form" action="/data?entity=0&opt=0" method="POST">
				<div class='form-group'>
					<div class="col-sm-2">
						<label for='uid' class='control-label'
							style='vertical-align: middle'>User ID: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="uid" name="uid"
							value="0" readonly>
					</div>
				</div>
				<div class='form-group'>
					<div class="col-sm-2">
						<label for='lastname' class='control-label'
							style='vertical-align: middle'>Last Name: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="lastname"
							name="lastname" placeholder="Last Name" value="">
					</div>
					<div class="col-sm-2">
						<label for='firstname' class='control-label'
							style='vertical-align: middle'>First Name: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="firstname"
							name="firstname" placeholder="First Name" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='alias' class='control-label'
							style='vertical-align: middle'>Alias: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="alias" name="alias"
							placeholder="Alias" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='pwd' class='control-label'
							style='vertical-align: middle'>Password: </label>
					</div>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pwd" name="pwd"
							placeholder="Password" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='telephon' class='control-label'
							style='vertical-align: middle'>Telefon: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="telephon"
							name="telephon" placeholder="Telefon" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='Email' class='control-label'
							style='vertical-align: middle'>Email: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="email" name="email"
							placeholder="Email" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='country' class='control-label'
							style='vertical-align: middle'>Country: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="country"
							name="country" placeholder="Country" value="">
					</div>
					<div class="col-sm-2">
						<label for='province' class='control-label'
							style='vertical-align: middle'>Province: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="province"
							name="province" placeholder="Province" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='city' class='control-label'
							style='vertical-align: middle'>City: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="city" name="city"
							placeholder="City" value="">
					</div>
					<div class="col-sm-2">
						<label for='zipcode' class='control-label'
							style='vertical-align: middle'>Zipcode: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="zipcode"
							name="zipcode" placeholder="Zipcode" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label for='address' class='control-label'
							style='vertical-align: middle'>Address: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="address"
							name="address" placeholder="Address" value="">
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-sm-2">
						<button type="button" class="btn btn-danger delete">Delete</button>
					</div>
					<div class="col-sm-offset-8 col-sm-2 save">
						<button type=submit class="btn btn-success save">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		window.json_customer = <%=json_customers %>;
		window.json_districts ={};
	</script>

</body>
</html>