<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" errorPage="Error.jsp" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrative Login</title>
<link rel="stylesheet" href="CSS/styles.css" type="text/css">
</head>
<body>
<div>
<jsp:include page="head.jsp"></jsp:include>
</div>
<form method="post" action="AuthServlet">
<div class="log">
<center>
<h1> Login </h1>
Username <input type="text" name="txt1" required/><br><br>
Password <input type="password" name="pass1" id="pass" required/><br><br>
<div class="button">
<input type="submit" class="button" value="Login" style="width: 75px;"/><br>
</div>
</center>
</div>
</form><br><br><br>
<div>
<jsp:include page="foot.jsp"></jsp:include>
</div>
</body>
</html>