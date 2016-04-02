package com.ankit.web;

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
 * Servlet implementation class CategoryCreate
 */
public class CategoryCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryCreate() {
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
		else if((String) sess.getAttribute("uname")==null) response.sendRedirect("Error.jsp");  
		else {PrintWriter out=response.getWriter();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		response.getWriter().print("<!DOCTYPE html><link rel='stylesheet' href='CSS/styles.css' type='text/css' />");
		try{
			String ctgry_name=request.getParameter("ctgry_txt");
			String ctgry_type=request.getParameter("ctgry_type");
			con=ConnectionProvider.getDbConnection();
			pstmt=con.prepareStatement("select * from category where ctgry_name = ?");
			pstmt.setString(1,ctgry_name);
			rs=pstmt.executeQuery();
			if(rs.next()) out.print("<h1>Category name already exists... Redirecting in 3 secs...!!!</h1>");
			else {
					pstmt2 = con.prepareStatement("insert into category (ctgry_name,ctgry_type,ctgry_home) values ( ? , ? , ? )");
					pstmt2.setString(1,ctgry_name);
					pstmt2.setString(2,ctgry_type);
					pstmt2.setInt(3, 1);
					pstmt2.executeUpdate();
					out.print("<!DOCTYPE html><h1>Category created ....Redirecting in 3 secs...!!!</h1>");
					RequestDispatcher rd=request.getRequestDispatcher("CategoryUpdate");
					rd.include(request, response);
					pstmt2.close();
				}
			response.getWriter().print("<br><br><a href='AdminPanel.jsp'> Click here </a> for manual redirect ... !!!");
			 response.setHeader("Refresh", "3; URL=AdminPanel.jsp");
			}
		catch(SQLException ex){
			System.out.print("SQL Exception caught... "+ex);
			}
		finally{
			try {
				rs.close();
				pstmt.close();
			    con.close();
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		
		}
	  }
	}