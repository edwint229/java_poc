<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
    String context = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Online Vote System</title>

<link href="<%=context%>/resources/img/ico/favicon.ico" rel="shortcut icon">
<link href="<%=context%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=context%>/resources/css/login.css" rel="stylesheet">
<!-- html5shiv to support IE8 or below support HTML5 elements -->
<!-- respond to support IE8 or below support media queries -->
<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/html5shiv.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/bootstrap/js/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="container">
		<form:form id="form" method="post" modelAttribute="loginForm" action="processLogin.do" cssClass="form-signin" role="form">
			<div id="messageBox" class="row">
				<c:if test="${not empty message}">
					<div class="alert alert-danger">
						<div id="message" class="success">${message}</div>
					</div>
				</c:if>
			</div>
			<div class="row">
				<h2 class="form-signin-heading">请登录</h2>
			</div>
			<div class="row">
				<div class="input-group">
					<form:input path="userId" type="text" class="form-control" placeholder="邮箱" required="required" autofocus="autofocus"></form:input>
					<span class="input-group-addon" id="basic-addon2">@qq.com</span>
				</div>
			</div>
			<div class="row">
				<form:password path="password" class="form-control" placeholder="密码" required="required"></form:password>
			</div>
			<div class="row">
				<label class="checkbox"> <form:checkbox path="checked" value="true" /> 记住我
				</label>
			</div>
			<div class="row">
				<div class="btn-group btn-group-justified" role="group">
					<div class="btn-group" role="group">
						<button class="btn btn-lg btn-success btn-block" type="submit">登录</button>
					</div>
					<div class="btn-group" role="group">
						<button id="btnSendPwd" class="btn btn-lg btn-default btn-block" type="button">获得初始密码</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<script type="text/javascript" src="<%=context%>/resources/js/3rdpart/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/login.js"></script>
</body>
</html>