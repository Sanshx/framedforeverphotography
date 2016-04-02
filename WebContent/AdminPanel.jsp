<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Administrative Panel</title>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" href="CSS/styles.css" type="text/css" />
<link rel="stylesheet" href="CSS/styleshome.css" type="text/css" />
<link rel="stylesheet" href="CSS/stylesadmin.css" type="text/css" />
<script src="JS/AdminContent.js"></script>
</head>
<body onload="picname1('<%=request.getContextPath()%>');picname2('<%=request.getContextPath()%>');cat_home();edit_cat();">
	<h1>&nbsp;&nbsp;&nbsp;
		<% 
	String uname=(String)session.getAttribute("uname");
	out.print("Welcome "+uname.toUpperCase());
	%>
	</h1>
	<table style="position:absolute; top:10px; right:100px;" cellspacing=10>
	<tr>
	<td>
	<form action="AdminPanel.jsp"><input type=submit class=button value=Reload></form>
	</td>
	<td>
	<form action="Logout"><input type=submit class=button value=Logout></form>
	</td>
	</tr>
	<tr>
	<td colspan=2>
	<form action="Home.jsp"><input type=submit class=button style="width: auto;" value=FramedForeverPhotography.com></form>
	</td>
	</tr>
	</table>
	<br>
	<br>
	<%! ArrayList<String> al1; %>
	<% ArrayList<String> al1 = (ArrayList<String>) application.getAttribute("category"); %>
	<br>
	
	
	<fieldset>
		<legend class="legendtext">Upload file</legend>
		Select a file to upload: <br />
		<form action="FileUpload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" style='width: 500px;' size="200" required/><br><br>
			Select a category: <select name="ctg_menu">
				<% for(String s:al1) out.println("<option value='"+s+"'>"+s+"</option>"); %>
			</select> <br><br> Write a caption: <input type="text"
				style="color: #000000; width: 300px;" name="cptn" /> <br><br>
			<input type="submit" class="button" value="Upload File" />
		</form>
	</fieldset>
	<br>
	
	
	<br>
	<fieldset>
		<legend class="legendtext">Create new Category</legend>
		<form action="CategoryCreate" method="post">
			Category name: <input type="text" style="color: #000000; width: 300px;"
				name="ctgry_txt" required><br><br>
				<input type="radio" name="ctgry_type" value="E" checked="checked">Events Photography <br>
				<input type="radio" name="ctgry_type" value="G">General photography <br><br>
				 <input type="submit"
				class="button" value="Create">
		</form>
	</fieldset>
	<br>
	<br>
	
	
	<fieldset>
	<legend class="legendtext"> Photographs for Home page </legend>
	<form action="CatHomeUpdate">
	<div id="ctgry_home"></div><br>
	<input type="submit" class="button" value="Update">
	</form>
	</fieldset>
	
	<br><br>
	<form action="DeleteServlet" method="post">
		<fieldset>
			<legend class="legendtext"> Delete photographs and Categories </legend>
			Select a category: <select name="ctg_menu" onchange="picname1('<%=request.getContextPath()%>')"
				id="selectcat1">
				<% for(String s:al1) out.println("<option value='"+s+"'>"+s+"</option>"); %>
			</select><br><br> Name of photos: <select name="pic_menu" id="selectpic1" onchange="forthumb('selectpic1')">
			</select><br><br> <input type="submit" class="button" value="Delete Photo"
				name="btn"> <input type="submit" class="button"
				value="Delete Category" name="btn" style="width: 150px;"><br>
				<div id="deletethumb"><center><img id="delete"></center></div>
		</fieldset>
	</form>
	
	
	<br><br>
		<form action="EditPhoto" method="post">
		<fieldset>
			<legend class="legendtext"> Edit Photographs Details </legend>
			Select a category (Current Category): <select name="ctg_menu" onchange="picname2('<%=request.getContextPath()%>')" id="selectcat2">
			<% for(String s:al1) out.println("<option value='"+s+"'>"+s+"</option>"); %>
			</select>
			<br><br> Name of photos: <select name="pic_menu" onchange="details()" id="selectpic2">
			</select><br><br> 
			Edit Caption : <input type="text" name="edit_cap" id="cptn" style="width: 300px; color: #000000"/>
			<br><br>Change Category to (New Category) : <select name="ctg_to_edit" id="selectcat3">
				<% for(String s:al1) out.println("<option value='"+s+"'>"+s+"</option>"); %>
			</select>
			<br><br><input type="submit" class="button" value="Update"/>
			<div id="editthumb"><center><img id="edit"></center></div>
		</fieldset>
	</form>
	<br><br>
	<br><br>
	
	<fieldset>
	<legend class="legendtext"> Edit Categories Details </legend>
	<form action="CatDetailUpdate" method=post>
	Select a category: <select name="editcat" onchange="edit_cat()" id="editcat">
	 <% for(String s:al1) out.println("<option value='"+s+"'>"+s+"</option>"); %>
	</select><br><br>
	Edit Category name (150 char max)<input type="text" name="cat_name_edit" id="cat_name_edit" style="width: 300px; color: #000000"><br><br>
	<input type="radio" name="ctgry_edit" id="er1" value="E">Events Photography <br>
				<input type="radio" name="ctgry_edit" id="er2" value="G">General photography <br><br>
				 <input type="submit"
				class="button" value="Update">
	</form>
	</fieldset>
</body>
</html>
