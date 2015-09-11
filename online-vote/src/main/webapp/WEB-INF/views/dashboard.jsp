<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
    String rootUrl = "http://" + request.getServerName() + ":"
					+ request.getServerPort();
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
<title>Online Vote Home</title>
<!-- links -->
<link href="<%=context%>/resources/img/ico/favicon.ico" rel="shortcut icon">
<link href="<%=context%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=context%>/resources/css/dashboard.css" rel="stylesheet">

<!-- excanvas to support IE8 or below support canvas -->
<!-- html5shiv to support IE8 or below support HTML5 elements -->
<!-- respond to support IE8 or below support media queries -->
<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/excanvas.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/html5shiv.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	var config = {};
	config.contextPath = '<%=context%>';
</script>
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
	<div class="container">
		<div class="row">
			<div class="row">
				<h2 class="form-signin-heading">投票结果</h2>
			</div>
			<div class="row">
				<canvas id="voteResult" width="500" height="400"></canvas>
			</div>
		</div>
		<br /> <br />
		<div id="messageBox" class="row"></div>
		<div id="voteOptions" class="row"></div>
		<div class="row">
			<div class="col-sm-3">
				<button id="submitVote" class="btn btn-lg btn-success btn-block" type="button">提交</button>
			</div>
		</div>
	</div>
	<!-- Dependency js, placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/Chart.min.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/dashboard.js"></script>
</body>
</html>