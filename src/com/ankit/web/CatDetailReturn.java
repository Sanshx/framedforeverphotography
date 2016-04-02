package com.ankit.web;

import java.io.IOException;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CatDetailReturn
 */
public class CatDetailReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatDetailReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String catname=request.getParameter("catname");
		String result="";
		try{
			Connection con=com.ankit.db.ConnectionProvider.getDbConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select ctgry_type from category where ctgry_name="+catname+"");
			rs.next();
			result+=rs.getString("ctgry_type");
			rs.close();
			st.close();
			con.close();
		}catch(Exception e) {System.out.print("Exception occured in CatDetailRetrun servlet: "+e);}
		response.getWriter().print(result);
	}

}
