<%@ page language="java" errorPage="Error.jsp" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META NAME="description" CONTENT="Wedding , Parties and Functions Photographer in Renton , Seattle USA. I'm a freelance photographer with experience in Product, Family, Wedding and Portrait photography. Contact me for any photography services.">
<META NAME="keywords" CONTENT="Photographer , Renton , Seattle , USA, Ankit , Saxena">
<title>Contact Us</title>
<link rel="stylesheet" href="CSS/styles.css" type="text/css" />
<script src="JS/FacebookJs.js"></script>
<style>
#contact a {
	background-color: #444444;
}
</style>
<script type="text/javascript">
	function dispmsg(u) {
		if(u=="mailsent") {
			document.getElementById("dispmsgbox").innerHTML="Email Sent ...";
			document.getElementById("sendbtn").disabled=true;
			document.getElementById("sendbtn").style.background='#222222';
		}
		else document.getElementById("sendbtn").disabled=false;
	}
</script>
</head>
<body onload="dispmsg('<%=request.getParameter("u")%>')">
	<div>
	<jsp:include page="head.jsp"></jsp:include>
	</div>
	<br>
	<table align="center" cellspacing=25 cellpadding=25>
<tr>
<td bgcolor="#111111" width="400" style="font-family: sans-serif; font-size: 12px;">
<!-- <div style="width: 400px;position: relative;"> -->
		<h3>About Me</h3>
		I'm a freelance photographer with experience in Product, Family, Wedding and Portrait photography. Contact me for any photography services.<br>
		<br> <img alt="Ankit" src="Res/pic_gimp.jpg" height="250" width="380">
<!-- </div> -->
</td>
<td bgcolor="#111111" rowspan=2>
<div class="contact"
		style="width: 400px;">
		<h3>Contact Me</h3>
		<b>Ankit Saxena<br>
		Phone:</b> +1 510-967-2972<br> <b>Email:</b>&nbsp;&nbsp;ankitsaxena.photography@gmail.com<br><b>Location:</b>&nbsp;&nbsp;Renton, Washington, U.S.
		<h3>Or leave me a message ...</h3>
		<form action="SendMail.jsp" method="POST">
			<br>Name* &nbsp;&nbsp;&nbsp;<input type="text" name="txt1" style="position: relative; width: 280px; right: 0px;"
				required /><br> <br>Email* &nbsp;&nbsp;&nbsp;<input
				type="text" name="txt2" style="position: relative; width: 280px; right: 0px;" required /><br> <br>Message<br>
			<textarea name="txt3" style="height: 120px; width: 360px;" required></textarea>
			<br>
			<br>
			<div id="dispmsgbox" style="color: #00aaff;"></div>
			<input type="submit" class="button" id="sendbtn"
				style="position: relative; width: 75px; left: 40%;" value="Send" />
		</form>
 	</div></td>
</tr>
<tr>
<td bgcolor="#111111">
	<div class="contact"
		style="width: 400px; height: 50px;">
		<div id="fb-root">
			<div class="fb-like" 
				data-href="https://www.facebook.com/AnkitSaxena.Photography/"
				data-width="400" data-height="The pixel height of the plugin"
				data-colorscheme="dark" data-layout="standard" data-action="like"
				data-show-faces="true" data-send="true"></div>
		</div>
	</div>
</td>
</tr>
</table>
	<br>
	<br>
	<div>
	<jsp:include page="foot.jsp"></jsp:include>
	</div>
</body>
</html>