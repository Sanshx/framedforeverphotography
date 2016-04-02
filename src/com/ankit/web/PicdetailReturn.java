package com.ankit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ankit.db.ConnectionProvider;
/**
 * Servlet implementation class PicdetailReturn
 */
public class PicdetailReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicdetailReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml");
		String cat=request.getParameter("cat");
		String pic=request.getParameter("pic");
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		try {
			con=ConnectionProvider.getDbConnection();
			st=con.prepareStatement("SELECT photo_captn from photos INNER JOIN category where photos.ctgry_id=category.ctgry_id and ctgry_name= ? and photo_name = ?");
			st.setString(1, cat);
			st.setString(2, pic);
			rs=st.executeQuery();
			rs.first();
			out.print("<PC>"+rs.getString("photo_captn")+"</PC>");
			con.close();
			}
		catch(Exception e) {
			System.out.print("Exception occured in PicdetailReturn Servlet "+e);
			}
		finally{
			try {
				rs.close();
				st.close();
			    con.close();
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
