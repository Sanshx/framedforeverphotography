package com.ankit.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ankit.db.ConnectionProvider;
/**
 * Servlet implementation class CategorySpecInit
 */
public class CategorySpecInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorySpecInit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess=request.getSession(false);
		if(sess == null) sess=request.getSession(true);
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String cat_name=request.getParameter("ctgry_name");
		try {
			con = ConnectionProvider.getDbConnection();
			pstmt = con.prepareStatement("select photos.photo_hash,photos.photo_captn from photos INNER JOIN category where photos.ctgry_id=category.ctgry_id and category.ctgry_name = ?");
			pstmt2 = con.prepareStatement("select photo_hash,photo_captn from photos order by photo_time desc limit 10");
			pstmt.setString(1,cat_name);
			if(!cat_name.equals("New")) rs = pstmt.executeQuery();
			else if(cat_name.equals("New")) rs = pstmt2.executeQuery();
			}
		catch(Exception e) {
			System.out.print("Exception occured in Category Specific servlet...");
			}
		finally{
			sess.setAttribute("rs", rs);
//	rs.close();
//	pstmt.close();
//	pstmt2.close();
//  con.close();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
