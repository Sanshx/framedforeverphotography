package com.ankit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CatHomeUpdate
 */
public class CatHomeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatHomeUpdate() {
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
		else try{
			PrintWriter out=response.getWriter();
			out.print("<!DOCTYPE html><link rel='stylesheet' href='CSS/styles.css' type='text/css' />");
			Integer ctgry_id=0;
			Connection con=com.ankit.db.ConnectionProvider.getDbConnection();
			Statement st=con.createStatement();
			Statement st1=con.createStatement();
			ResultSet rs=st.executeQuery("select ctgry_id from category");
			while(rs.next()) {
				ctgry_id=rs.getInt("ctgry_id");
				if(request.getParameter(String.valueOf(ctgry_id))!=null) st1.executeUpdate("update category set ctgry_home=1 where ctgry_id="+ctgry_id+"");
				else st1.executeUpdate("update category set ctgry_home=0 where ctgry_id="+ctgry_id+"");
			}
			st.close();
			st1.close();
			rs.close();
			con.close();
			out.print("<h1>Update Successfull ...Redirecting in 3 secs ...!!!</h1>");
			out.print("<br><br><a href='AdminPanel.jsp'> Click here </a> for manual redirect ... !!!");
			response.setHeader("Refresh", "3; URL=AdminPanel.jsp");
		}catch(Exception e){System.out.print("Exception occured in CatHomeUpdate servlet: "+e);}
	}

}
