package com.ankit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class CategorySpecDir
 */
public class CategorySpecDir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorySpecDir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	HttpSession sessCat=request.getSession(false);
	ResultSet rs=(ResultSet)sessCat.getAttribute("rs");
	PrintWriter out=response.getWriter();
	response.setContentType("text/xml");
	try {
		if(rs.next()) {
			if(!rs.getString("photo_captn").equals("")) out.print("<IMAGE><HASH>"+rs.getString("photo_hash")+"</HASH><CAPTION>"+rs.getString("photo_captn")+"</CAPTION></IMAGE>");
			else  out.print("<IMAGE><HASH>"+rs.getString("photo_hash")+"</HASH><CAPTION>...</CAPTION></IMAGE>");
			sessCat.setAttribute("rs", rs);
		}
	else out.print("<IMAGE><HASH>END</HASH><CAPTION>END</CAPTION></IMAGE>");
	} catch(Exception e) {System.out.print("Exception occured in Category Specifid Directive servlet...");}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
