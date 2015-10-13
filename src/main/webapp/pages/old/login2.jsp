<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ include file="../stub/jstl-stub.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="welcome-page-title" /></title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
<script type="text/javascript" src="./jsresources/bootstrap.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script type="text/javascript" src="./jsresources/bootstrap-tab.js"></script>

</head>

<body>

	<div id="welcome_title">
		<h1><fmt:message key="welcome-title"/></h1>
	</div>
	
	<h3><fmt:message key="welcome-login-header"/></h3>
	
	<div class="container">
		<div id="content">
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
				<li class="active"><a href="#admin" data-toggle="tab">Administrator</a></li>
				<li><a href="#student" data-toggle="tab">Student</a></li>
			</ul>
			<div id="my-tab-content" class="tab-content">
				<div class="tab-pane active" id="admin">
					<h3>Admin</h3>
				</div>
				<div class="tab-pane" id="student">
					<h3>Student</h3>
				</div>
			</div>
		</div>
	</div>

</body>
</html>