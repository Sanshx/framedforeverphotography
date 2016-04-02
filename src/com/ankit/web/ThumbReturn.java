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

import com.ankit.db.ConnectionProvider;
/**
 * Servlet implementation class ThumbReturn
 */
public class ThumbReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pic=request.getParameter("pic");
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			con=ConnectionProvider.getDbConnection();
			st=con.prepareStatement("select photo_hash from photos where photo_name = ?");
			st.setString(1, pic);
			rs=st.executeQuery();
			rs.next();
			response.getWriter().print(rs.getString("photo_hash"));
		//	con.close();
			} 
		catch(Exception e) {
			System.out.print("Exception in Thumb Return serlvet..."+e);
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
