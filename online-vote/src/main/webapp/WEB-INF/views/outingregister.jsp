<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String rootUrl = "http://" + request.getServerName() + ":" + request.getServerPort();
	String context = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Team Building人数统计</title>
<!-- links -->
<link href="<%=context%>/resources/img/ico/favicon.ico" rel="shortcut icon">
<link href="<%=context%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=context%>/resources/css/outingregister.css" rel="stylesheet">

<!-- excanvas to support IE8 or below support canvas -->
<!-- html5shiv to support IE8 or below support HTML5 elements -->
<!-- respond to support IE8 or below support media queries -->
<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/excanvas.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/html5shiv.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/jquery-1.11.0.min.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Online Vote System</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value="/logout.do" />">Log out</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Welcome, ${userInfo.username}</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<form id="outingRegisterForm" method="post" action="processOutingRegister.do" class="form-register" role="form">
			<div id="messageBox" class="row"></div>
			<div class="row">
				<h2 class="form-signin-heading">Team Building人数统计</h2>
			</div>

			<div class="row">
				<div class="col-md-3">
					<h4>姓&nbsp;&nbsp;&nbsp;名:</h4>
				</div>
				<div class="col-md-8">
					<input name="name" type="text" class="form-control" placeholder="必填" required="required" autofocus="autofocus" maxlength="4"></input>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-8">
					<label class="checkbox-inline"> <input type="radio" name="gender" value="男">男
					</label> <label class="checkbox-inline"> <input type="radio" name="gender" value="女">女
					</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<h4>身份证:</h4>
				</div>
				<div class="col-md-8">
					<input name="inditifyNo" type="text" class="form-control" placeholder="必填" required="required" maxlength="18"></input>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<h4>手机号:</h4>
				</div>
				<div class="col-md-8">
					<input name="phoneNo" type="text" class="form-control" placeholder="必填" required="required" maxlength="11"></input>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<h4>备&nbsp;&nbsp;&nbsp;注:</h4>
				</div>
				<div class="col-md-8">
					<input name="remark" type="text" class="form-control" placeholder="可选（如和谁住一间）" maxlength="128"></input>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-7">
					<button id="submitRegister" class="btn btn-lg btn-success btn-block" type="button">提交</button>
				</div>
				<div class="col-md-2">
					<a href="javascript:void;" class="linkBtn" id="modifyLink">修改</a>
				</div>
			</div>
		</form>
	</div>
	<!-- Dependency js, placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/Chart.min.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/outingregister.js"></script>
</body>
</html>