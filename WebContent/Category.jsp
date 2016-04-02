<%@ page language="java" errorPage="Error.jsp" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%out.print(request.getParameter("ctgry_name").replaceAll("_", " "));%></title>
<%if("New".equals(request.getParameter("ctgry_name"))) out.print("<style>#new a{background-color: #444444;}</style>"); else out.print("<style>#cat>a{background-color: #444444;}</style>"); %>
<link rel="stylesheet" href="CSS/styles.css" type="text/css" />
<script src="JS/catjs.js"></script>
</head>
<body onload="pageUp();chromeUp();init('<%=request.getParameter("ctgry_name").replaceAll("_", " ")%>');pageScroll();">
	<div>
	<jsp:include page="head.jsp"></jsp:include>
	</div>
	<br>
	<!--div id="catname">
	<h2><u><%out.print(request.getParameter("ctgry_name").replaceAll("_", " "));%></u></h2>
	</div-->
	<center>
		<div id="catimg" style="position:relative; width: 1300px; height: 800px;// z-index: -1;">
			<div class="arrowbar" onclick="changeprev()">
				<div class="arrow"><img src="Res/left.svg" height=40px></div>
			</div>
			<div id="inprev" onclick="changeprev()" style="position: absolute; left:50px; float: left; width: 600px; height: 800px; z-index: 10;"></div>
			<div id="innext" onclick="changenext()" style="position: absolute; left:650px; float:left; width: 600px; height: 800px; z-index: 10;"></div>
			<div style="position: relative; background-color: #111111; display: inline; float: left; width: 1200px; height: 800px; max-width: 1200px;// z-index: -2;">
				<img id="loading" src="Res/load.gif" style="position: absolute; left: 574px; top: 374px;">
				<img id="centerimg" style="position: relative; overflow: hidden;// z-index: -1;">
			</div>
			<div class="arrowbar" onclick="changenext()">
				<div class="arrow"><img src="Res/right.svg" height=40px></div>
			</div>
		</div>
		<br>
		<div id="caption"></div><br>
		<div id="catthumb" style="width: 1070px; height: 120px; background-color: #111111; opacity:0.9">
<div class="arrowbarsmall" onclick="thumbprev()"><div class="arrow" style="margin-top: -10px;"><img src="Res/left.svg" height=20px></div></div>
<div style="background-color: #111111; display: inline;float: left;width: 1020px; height: 120px;max-width:1020px;z-index:-1;">
<div class="catsub" id="catsub1" onclick="imgchange(1)"><img class="catthumbimg" id="thumb1"></div>
<div class="catsub" id="catsub2" onclick="imgchange(2)"><img class="catthumbimg" id="thumb2"></div>
<div class="catsub" id="catsub3" onclick="imgchange(3)"><img class="catthumbimg" id="thumb3"></div>
<div class="catsub" id="catsub4" onclick="imgchange(4)"><img class="catthumbimg" id="thumb4"></div>
<div class="catsub" id="catsub5" onclick="imgchange(5)"><img class="catthumbimg" id="thumb5"></div>
<div class="catsub" id="catsub6" onclick="imgchange(6)"><img class="catthumbimg" id="thumb6"></div>
</div>
<div class="arrowbarsmall" onclick="thumbnext()"><div class="arrow" style="margin-top: -10px;"><img src="Res/right.svg" height=20px></div></div>
</div>
	</center>
	<br>
	<br>
	<div>
	<jsp:include page="foot.jsp"></jsp:include>
	</div>
</body>
</html>