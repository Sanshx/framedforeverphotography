package com.ankit.web;

import java.io.IOException;
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

import org.apache.commons.codec.digest.DigestUtils;

import com.ankit.db.ConnectionProvider;

/**
 * Servlet implementation class AuthServlet
 */
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String uname=request.getParameter("txt1");
			String upass=DigestUtils.md5Hex(request.getParameter("pass1"));
			int login=0;
			con=ConnectionProvider.getDbConnection();
			String psql = "SELECT username,password FROM adminlogin where username=?";
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1,uname);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(uname.equals(rs.getString(1))&&upass.equals(rs.getString(2))) {	
						login=1;
						break;
					}
				}
			if(login==1) {
				HttpSession sess=request.getSession(true);
				sess.setAttribute("uname", uname);
				RequestDispatcher rd=request.getRequestDispatcher("AdminPanel.jsp");
				rd.forward(request, response);
				}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");
				System.out.print("Login failed");
				rd.include(request, response);
				}
			}
		catch(Exception ex) {
			System.out.println("Exception occured...in auth servlet"+ex);
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