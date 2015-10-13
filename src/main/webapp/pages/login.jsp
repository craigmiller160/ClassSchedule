<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ include file="stub/jstl-stub.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="welcome-page-title" /></title>

<%@ include file="stub/bootstrap-stub.jsp" %>
<%@ include file="stub/bootstrap-tab-stub.jsp" %>
<link href="./pages/stylesheet.css" rel="stylesheet" type="text/css"/>
</head>

<body>

	<header class="header hf-color-login">
		<h1><fmt:message key="welcome-title"/></h1>
	</header>
	
	<div id="container-login" class="container-content">
		
		<div id="welcome-instruction" >
			<h3><fmt:message key="welcome-login-header"/></h3>
		</div>
		
		<form action="login.html" method="post">
		<div class="container" id="login-tabs-outer">
			<div id="login-tabs-inner">
				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
					<li class="active"><a href="#admintab" data-toggle="tab"><fmt:message key="login-tab-admin" /></a></li>
					<li><a href="#studenttab" data-toggle="tab"><fmt:message key="login-tab-student"/></a>
				</ul>
				<div id="my-tab-content" class="tab-content">
					<div class="tab-pane active login-tab-content" id="admintab">
						<h4><fmt:message key="login-as-admin" /></h4>
						<input class="btn login-btn" name="admin" type="submit" value="<fmt:message key="login-button"/>" />
					</div>
					<div class="tab-pane login-tab-content" id="studenttab">
						<h4><fmt:message key="login-as-student" /></h4>
						<input class="btn login-btn" name="student" type="submit" value="<fmt:message key="login-button"/>" />
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	
	<footer class="footer hf-color-login">
		<h4>Copyright 2015, CraigMiller160</h4>
	</footer>

</body>
</html>