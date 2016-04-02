<%@ page language="java" errorPage="Error.jsp" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>FramedForeverPhotography</title>
<META NAME="description" CONTENT="Wedding , Parties and Functions Photographer in Renton , Seattle USA.  I'm a freelance photographer with experience in Product, Family, Wedding and Portrait photography. Contact me for any photography services.">
<META NAME="keywords" CONTENT="Photographer , Renton , Seattle , USA, Ankit , Saxena">
<link rel="stylesheet" href="CSS/styles.css" type="text/css"/>
<link rel="stylesheet" href="CSS/styleshome.css" type="text/css"/>
<script src="JS/ImageChanger.js"></script>
<style>#home a{background-color: #444444;}</style>
</head>

<body  onload="init()">
<div>
<jsp:include page="head.jsp"></jsp:include>
</div>
<br><br>
<center><div id="main" style="position: relative; height: 600px; width: 900px;">
<img id='imgchange' src="Res/load_home.gif" style="position: absolute; top: 290px; left: 340px;">
</div></center>
<br><br>
<div>
<jsp:include page="foot.jsp"></jsp:include>
</div>
</body>
</html>