<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<head>
<link rel="shortcut icon" href="Res/favicon.ico">
<META NAME="description" CONTENT="Wedding , Parties and Functions Photographer in Renton , Seattle USA">
<META NAME="keywords" CONTENT="Photographer , Renton , Seattle , USA, Ankit , Saxena">
<link rel="stylesheet" href="CSS/styleshead.css" type="text/css"/> 
<script src="JS/FacebookJs.js"></script>
</head>
<body>
<div id="head" align="center">
<span><ul id="navul">
<li class="navlist" id="home"><a href="Home.jsp">Home</a></li>
<li class="navlist" id="cat"><a id="cata" href=Albums.jsp>Albums</a>
  <% 
	ArrayList<String> al = (ArrayList<String>) application.getAttribute("category");
 //	out.println("<ul>");
 	try{
 		if(al == null ) {
 			RequestDispatcher rd=request.getRequestDispatcher("CategoryUpdate");
 			rd.include(request, response);
 			al = (ArrayList<String>) application.getAttribute("category");
 		}
 	//	for(String s:al) out.println("<li id='catli'><a href=Category.jsp?ctgry_name="+s.replaceAll(" ", "_")+">"+s+"</a></li>");
 	}
 	catch(Exception e){System.out.print("Head= "+e);};  
 //	out.println("</ul>");
 	%>  
</li>
<li class="navlist" id="new"><a href="Category.jsp?ctgry_name=New">New</a></li>
<li class="navlist" id="about"><a href="AboutMe.jsp">About Me</a></li>
<li class="navlist" id="contact"><a href="Contactus.jsp">Contact Us</a></li>
<li class="navlist" id="photo">&nbsp;&nbsp;&nbsp;Framed Forever Photography</li>
</ul></span>
<div style="position: relative; left: 100px;" class="fb-like" data-href="https://www.facebook.com/AnkitSaxena.Photography" data-layout="button_count" data-action="like" data-show-faces="false" data-share="false"></div>
</div>
</body>