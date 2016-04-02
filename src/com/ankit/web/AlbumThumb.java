package com.ankit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import java.sql.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Servlet eimplementation class AlbumThumb
 */
public class AlbumThumb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumThumb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int a=0,nofpic=0,pic=0;
		Random r=new Random();
		try{
			con=com.ankit.db.ConnectionProvider.getDbConnection();
			st=con.createStatement();
			rs=st.executeQuery("select ctgry_id from category where ctgry_name='"+name+"'");
			rs.next();
			a=rs.getInt(1);
			rs=st.executeQuery("select photo_hash from photos where ctgry_id="+a);
			while(rs.next()) nofpic++;
			pic=r.nextInt(nofpic+1);
			if(pic==0) pic=1;
			rs.absolute(pic);
			response.getWriter().print(rs.getString(1));
			rs.close();
			st.close();
			con.close();
		}catch(Exception e) {System.out.print("Exception occured in AlbumThumb "+e);}
		
	}

}
