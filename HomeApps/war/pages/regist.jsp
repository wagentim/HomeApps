<%@page import="cn.wagentim.homeapps.entities.CustomerEntity"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>HomeApps - Register New User</title>
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
					<li><a href="/pages/regist.jsp">Register New User<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<div class="container col-xs-offset-1 col-xs-8 edit">
			<form class="form-horizontal myform" role="form" action="/data?entity=0&opt=0" method="POST">
				<div class='form-group'>
					<div class="col-sm-2">
						<label for='uid' class='control-label'
							style='vertical-align: middle'>ID: </label>
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
							style='vertical-align: middle'>Telefon Number: </label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="telephon"
							name="telephon" placeholder="Telefon Number" value="">
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
							style='vertical-align: middle'>ZipCode: </label>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="zipcode"
							name="zipcode" placeholder="ZipCode" value="">
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
						<button type="button" class="btn btn-danger delete">Clear</button>
					</div>
					<div class="col-sm-offset-11 save">
						<button type=submit class="btn btn-success save">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>