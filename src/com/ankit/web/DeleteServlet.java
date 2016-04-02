package com.ankit.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ankit.db.ConnectionProvider;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess=request.getSession(false);
		if(sess == null ) response.sendRedirect("Error.jsp");
		else if((String)sess.getAttribute("uname")==null) response.sendRedirect("Error.jsp");
		else {
			PrintWriter out=response.getWriter();
		
	String btn=request.getParameter("btn");
	String cat_name=request.getParameter("ctg_menu");
	String pic_name=null;
	String filePath=null;
	String thumb =null;
	Connection con=null;
	PreparedStatement st=null;
	PreparedStatement st2=null;
	ResultSet rs=null;
	String hash=null;
	
	con=ConnectionProvider.getDbConnection();
	out.print("<!DOCTYPE html><link rel='stylesheet' href='CSS/styles.css' type='text/css' />");
	if(btn.equals("Delete Photo")) {
		pic_name=request.getParameter("pic_menu");
		filePath = getServletContext().getInitParameter("file-upload"); 
	    thumb = getServletContext().getInitParameter("thumb");
	    try {
	    st=con.prepareStatement("select photo_hash from photos where photo_name = ?");
	    st.setString(1,pic_name);
	    rs=st.executeQuery();
	    rs.first();
	    hash=rs.getString("photo_hash");
	    }
	    catch(Exception e) {System.out.print("Error occured while deleting photos...."+e);}
	File f_image=new File(filePath+hash+".JPG");
	File f_thumb=new File(thumb+hash+".JPG");
	if(f_image!=null) f_image.delete();
	if(f_thumb!=null) f_thumb.delete();
	    try {
	    	st2=con.prepareStatement("delete from photos where photo_hash = ?");
	    	st2.setString(1,hash);
	    	st2.executeUpdate();
	    } catch(Exception e) {System.out.print("Exception occured at deleting photo from database...");}
	    out.print("<!DOCTYPE html><h1>Photo Deleted ....Redirecting in 3 secs ...!!!</h1>");
	}
	
	else if (btn.equals("Delete Category")) {
		try {
			st=con.prepareStatement("select photos.photo_name from photos INNER JOIN category where photos.ctgry_id=category.ctgry_id and category.ctgry_name = ?");
			st.setString(1,cat_name);
			rs = st.executeQuery();
			st2 = con.prepareStatement("delete from category where ctgry_name = ?");
			if(!rs.next()) {
				st2.setString(1,cat_name);
				st2.executeUpdate();
				out.print("<h1>Category Deleted ...Redirecting in 3 secs ...!!!</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("CategoryUpdate");
				rd.include(request, response);
				}
			else out.print("<h1>Category must be empty before deleting ...Redirecting in 3 secs ...!!!</h1>");
		}catch(Exception e) {System.out.print("Exception occured at deleting category ....");}
	}
	out.print("<br><br><a href='AdminPanel.jsp'> Click here </a> for manual redirect ... !!!");
	response.setHeader("Refresh", "3; URL=AdminPanel.jsp");
	try {
		rs.close();
		st.close();
		st2.close();
	    con.close();
		}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	}
}
