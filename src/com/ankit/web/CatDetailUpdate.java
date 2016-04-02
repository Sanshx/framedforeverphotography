package com.ankit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CatDetailUpdate
 */
public class CatDetailUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatDetailUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess=request.getSession(false);
		if(sess == null ) response.sendRedirect("Error.jsp");
		else if((String)sess.getAttribute("uname")==null) response.sendRedirect("Error.jsp");
		else {
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html><link rel='stylesheet' href='CSS/styles.css' type='text/css' />");
		String catname=request.getParameter("editcat");
		String newcatname=request.getParameter("cat_name_edit");
		String ctgry_type=request.getParameter("ctgry_edit");
		try{
			Connection con=com.ankit.db.ConnectionProvider.getDbConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select ctgry_id from category where ctgry_name='"+catname+"'");
			rs.next();
			int id=rs.getInt("ctgry_id");
			st.executeUpdate("update category set ctgry_name='"+newcatname+"',ctgry_type='"+ctgry_type+"' where ctgry_id="+id+"");
			rs.close();
			st.close();
			con.close();
		}catch(Exception e) {System.out.print("Exception occured in CatDetailUpdate servlet: "+e);}
		RequestDispatcher rd=request.getRequestDispatcher("CategoryUpdate");
		rd.include(request, response);
		out.print("<h1>Update Successfull ...Redirecting in 3 secs ...!!!</h1>");
		out.print("<br><br><a href='AdminPanel.jsp'> Click here </a> for manual redirect ... !!!");
		response.setHeader("Refresh", "3; URL=AdminPanel.jsp");
	}
  }
}
