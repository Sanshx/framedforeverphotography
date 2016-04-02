package com.ankit.web;

import java.io.IOException;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatHomeReturn
 */
public class CatHomeReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatHomeReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String result="";
			Connection con=com.ankit.db.ConnectionProvider.getDbConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select ctgry_id,ctgry_name,ctgry_home from category");
			while(rs.next()) {
				if(rs.getInt("ctgry_home")==0)result+="<input type='checkbox' name='"+rs.getString("ctgry_id")+"' value='true'> "+rs.getString("ctgry_name")+"<br>";
				else if(rs.getInt("ctgry_home")==1)result+="<input type='checkbox' name='"+rs.getString("ctgry_id")+"' value='true' checked='checked'> "+rs.getString("ctgry_name")+"<br>";
				}
			response.getWriter().print(result);
			rs.close();
			st.close();
			con.close();
		}catch(Exception e) {System.out.print("\nException in CatHomeReturn: "+e);}
	}

}
