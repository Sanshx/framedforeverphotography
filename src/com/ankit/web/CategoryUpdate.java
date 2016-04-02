package com.ankit.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ankit.db.ConnectionProvider;

/**
 * Servlet implementation class CategoryUpdate
 */
public class CategoryUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=ConnectionProvider.getDbConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		ServletContext sc= request.getServletContext();
		ArrayList<String> alG =(ArrayList<String>) sc.getAttribute("categoryG");
		ArrayList<String> alE =(ArrayList<String>) sc.getAttribute("categoryE");
		ArrayList<String> al =(ArrayList<String>) sc.getAttribute("category");
		st = con.prepareStatement("select * from category ");
		rs = st.executeQuery();
		if(alG == null) alG=new ArrayList<String>();
		if(alE == null) alE=new ArrayList<String>();
		if(al == null) al=new ArrayList<String>();
		alG.clear();
		alE.clear();
		al.clear();
		while(rs.next()) {
			if((rs.getString("ctgry_type")).equals("G")) alG.add(rs.getString("ctgry_name")); 
			else alE.add(rs.getString(2));
			}
		al.addAll(alG);
		al.addAll(alE);
		sc.setAttribute("categoryG", alG);
		sc.setAttribute("categoryE", alE);
		sc.setAttribute("category", al);
		}
		catch(Exception e) {
		System.out.print("Exception occured in category update servlet..");
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
}
