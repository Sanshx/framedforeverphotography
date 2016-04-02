package com.ankit.web;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import com.ankit.db.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeImgReturn
 */
public class HomeImgReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeImgReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess=request.getSession(false);
		int flag=1,ttlpics=0,arryindx=0;
		Connection con = null;
		ResultSet rsc=null,rs=null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		int[] catarr=null;
		ArrayList<String> list;
		if(((String)request.getParameter("req")).equals("new")) 
			{
			if(sess!=null) sess.invalidate();
			sess=request.getSession();
			list=new ArrayList<String>();
			sess.setAttribute("flag",flag);
			sess.setAttribute("ttlpics",ttlpics);
			sess.setAttribute("arryindx",arryindx);
			sess.setAttribute("list", list);
			sess.setAttribute("catarr", catarr);
			}
		else list=(ArrayList<String>)sess.getAttribute("list");
		if((int)sess.getAttribute("flag")==1)
			{
			int tpic=0;
			flag=0;
			sess.setAttribute("flag", flag);
			catarr=(int[])sess.getAttribute("catarr");
			Random r=new Random();
			int nofcat=0,nofpic=0;
			int[] picarr=null;
			int x=0,ccindx=0,pcindx=0,imgpmnt=4;
			try{
				con=ConnectionProvider.getDbConnection();
				st=con.prepareStatement("select * from category where ctgry_home=1");
				rsc=st.executeQuery();
				rsc.beforeFirst();
				while(rsc.next()) nofcat++;
				rsc.beforeFirst();
				if(catarr==null) {
				catarr=new int[nofcat];
				for (int i = 0; i < nofcat; i++) {
					x=r.nextInt(nofcat)+1;
						for (int j = 0; j < i; j++) {
							if(catarr[j]==x) {
								x=r.nextInt(nofcat)+1;
								j=-1;
							}
						}
					catarr[i]=x;
					}
				sess.setAttribute("catarr", catarr);
				}
				list.clear();
				st2=con.prepareStatement("select photo_hash from photos where ctgry_id = ?");
				for(ccindx=0;ccindx<nofcat;ccindx++) {
					picarr=null;
					rs=null;
					nofpic=0;
					if(rsc.absolute(catarr[ccindx]))
						{
							st2.setInt(1,rsc.getInt(1));
							rs=st2.executeQuery();
						}
					rs.beforeFirst();
					while(rs.next()) nofpic++;
					rs.beforeFirst();
					picarr=new int[nofpic];
					for (int i = 0; i < nofpic; i++) {
						x=r.nextInt(nofpic)+1;
							for (int j = 0; j < i; j++) {
								if(picarr[j]==x) {
									x=r.nextInt(nofpic)+1;
									j=-1;
								}
							}
						picarr[i]=x;
					}
					for(pcindx=0;pcindx<nofpic&&pcindx<imgpmnt;pcindx++,tpic++) 
						{
						rs.absolute(picarr[pcindx]);
						list.add(rs.getString("photo_hash"));
						}
					sess.setAttribute("ttlpics", tpic);
				}
				}
			catch(Exception ex) {
				System.out.print("\nException in HomeImgReturn: "+ex);
				}
			finally{
				try {
					st.close();
					st2.close();
					rsc.close();
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		ttlpics=(int)sess.getAttribute("ttlpics");
		arryindx=(int)sess.getAttribute("arryindx");
		if(arryindx==ttlpics-1)
			{
			flag=1;
			sess.setAttribute("flag", flag);
			arryindx=0;
			sess.setAttribute("arryindx", arryindx);
			response.getWriter().print(list.get(ttlpics-1));
			}
		else
			{
			response.getWriter().print(list.get(arryindx));
			arryindx++;
			sess.setAttribute("arryindx", arryindx);
			}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	}
}
