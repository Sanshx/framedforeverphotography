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
 * Servlet implementation class PicnameReturn
 */
public class PicnameReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicnameReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		ResultSet rspic = null;
		try {
			String output="";
			String cat=request.getParameter("cat");
			con = ConnectionProvider.getDbConnection();
			st = con.prepareStatement("select ctgry_id from category where ctgry_name = ?");
			st.setString(1, cat);
			rs=st.executeQuery();
			rs.first();
			int ctg_no=rs.getInt(1);
			st2 = con.prepareStatement("select photo_name from photos where ctgry_id = ?");
			st2.setInt(1, ctg_no);
			rspic=st2.executeQuery();
			while(rspic.next())
				output=output.concat("<option value='"+rspic.getString(1)+"'>"+rspic.getString(1)+"</option>\n");
			response.getWriter().print(output);
			}
		catch(Exception e) {
			System.out.print("Exception occured while fetching name of photographs "+e);
			}
		finally{
			try {
				rs.close();
				rspic.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
