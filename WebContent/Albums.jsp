<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Albums</title>
<script src="JS/album.js"></script>
<link rel="stylesheet" href="CSS/stylesalbum.css" type="text/css" />
<link rel="stylesheet" href="CSS/styles.css" type="text/css" />
<style>
#cat a {
	background-color: #444444;
}
</style>
</head>
<body>
	<div><jsp:include page="head.jsp"></jsp:include></div><br>
	<h3>General Category</h3>
	<div class="album-container wrap" id="cat-container">
		<%
			ArrayList<String> alG = (ArrayList<String>) application.getAttribute("categoryG");
		 	try{
		 		if(alG == null ) {
		 			RequestDispatcher rd=request.getRequestDispatcher("CategoryUpdate");
		 			rd.include(request, response);
		 			alG = (ArrayList<String>) application.getAttribute("categoryG");
		 		}
		 		int i=1;
		 		for(String s:alG) {
		 			out.println("<div align='center' class='item-contain'><a href='Category.jsp?ctgry_name="+s+"'><span class='album-item' id='cat"+i+"'><script>thumbrtrn('cat"+i+"','"+s+"','"+request.getContextPath()+"');</script></span></a>"+s+"</div>");
		 			i++;
		 		}
		 	}
		 	catch(Exception e){System.out.print("Head= "+e);};  
		 %>
	</div>
	<br>
	<h3>Event Albums</h3>
	<div class="album-container wrap" id="event-container">
		<%
			ArrayList<String> alE = (ArrayList<String>) application.getAttribute("categoryE");
		 	try{
		 		if(alE == null ) {
		 			RequestDispatcher rd=request.getRequestDispatcher("CategoryUpdate");
		 			rd.include(request, response);
		 			alE = (ArrayList<String>) application.getAttribute("categoryE");
		 		}
		 		int i=1;
		 		for(String s:alE) {
		 			out.println("<div align='center' class='item-contain'><a href='Category.jsp?ctgry_name="+s+"'><span class='album-item' id='event"+i+"'><script>thumbrtrn('event"+i+"','"+s+"');</script></span></a>"+s+"</div>");
		 			i++;
		 		}
		 	}
		 	catch(Exception e){System.out.print("Head= "+e);};  
		 %>
	</div>
	<br><div><jsp:include page="foot.jsp"></jsp:include></div>
</body>
</html>