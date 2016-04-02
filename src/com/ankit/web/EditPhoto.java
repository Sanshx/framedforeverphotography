package com.ankit.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ankit.db.ConnectionProvider;

/**
 * Servlet implementation class EditPhoto
 */
public class EditPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPhoto() {
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
		HttpSession sess=request.getSession(false);
		if(sess == null ) response.sendRedirect("Error.jsp");
		else if((String)sess.getAttribute("uname")==null) response.sendRedirect("Error.jsp");
		String newcat=request.getParameter("ctg_to_edit");
		String picname=request.getParameter("pic_menu");
		String cptn=request.getParameter("edit_cap");
		Connection con=null;
		PreparedStatement st=null;
		PreparedStatement st2=null;
		ResultSet rs=null;
		response.getWriter().print("<!DOCTYPE html><link rel='stylesheet' href='CSS/styles.css' type='text/css' />");
		 try {
			 con=ConnectionProvider.getDbConnection();
			 st=con.prepareStatement("SELECT ctgry_id from category where ctgry_name = ?");
			 st.setString(1,newcat);
			 rs=st.executeQuery();
			 rs.first();
			 int c = rs.getInt("ctgry_id");
			 st2 = con.prepareStatement("UPDATE photos set ctgry_id = ? ,photo_captn = ? where photo_name = ?");
			 st2.setInt(1,c);
			 st2.setString(2,cptn);
			 st2.setString(3,picname);
			 int s=st2.executeUpdate();
			 if(s!=0) response.getWriter().print("<h1>Update Successfull...Redirecting in 3 secs...!!!</h1>");
			 else response.getWriter().print("<h1>Update Failed...Redirecting in 3 secs...!!!</h1>");
		 	} 
		 catch(Exception e) {
			 System.out.print("Exception occured in Edit Photo Servlet "+e);
			 }
		 finally{
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
		 response.getWriter().print("<br><br><a href='AdminPanel.jsp'> Click here </a> for manual redirect ... !!!");
		 response.setHeader("Refresh", "3; URL=AdminPanel.jsp");
	}

}
