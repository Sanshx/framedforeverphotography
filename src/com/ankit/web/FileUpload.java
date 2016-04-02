package com.ankit.web;

// Import required java libraries
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//import static org.imgscalr.Scalr.*;
import resize.image.*;

import com.ankit.db.ConnectionProvider;


public class FileUpload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private String thumb;
	private int maxFileSize = 50 * 1024 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	String fileName = null;
	String hash = null;
	String caption = null;
	String category = null;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement st = null;
	PreparedStatement st2 = null;
	int ctgry_id = 0;

	public void init() {
		filePath = getServletContext().getRealPath("/Pics/") + "/";
		thumb = getServletContext().getRealPath("/Thumbs/") + "/";
	}

	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		HttpSession sess=request.getSession(false);
		if(sess == null ) response.sendRedirect("Error.jsp");
		else if((String)sess.getAttribute("uname")==null) response.sendRedirect("Error.jsp");
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(filePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		response.getWriter().print("<!DOCTYPE html><link rel='stylesheet' href='CSS/styles.css' type='text/css' />");
		try {
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					fileName = fi.getName();
					String hashdata = fi.getString();
					hash = DigestUtils.md5Hex(hashdata);
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + hash + ".JPG");
					} else {
						file = new File(filePath + hash + ".JPG");
					}
					fi.write(file);
					out.println("<!DOCTYPE html><h1>Uploaded Filename: "
							+ fileName + "\n\nRedirecting in 3 secs ...!!!</h1>");
					imgResize(file, filePath + hash + ".JPG");
					thumbResize(file, thumb + hash + ".JPG");
				} else {
					category = fi.getString();
					caption = null;
					if (i.hasNext()) {
						fi = (FileItem) i.next();
						caption = fi.getString();
					}
					break;
				}
			}
		} catch (Exception ex) {
			System.out
					.println("Exception occured in upload servlet 1st part .. "
							+ ex);
		}
		try {
			con = ConnectionProvider.getDbConnection();
			st = con.prepareStatement("select ctgry_id from category where ctgry_name = ?");
			st.setString(1, category);
			rs = st.executeQuery();
			rs.next();
			ctgry_id = rs.getInt(1);
			st2 = con
					.prepareStatement("insert into photos (photo_name,photo_hash,photo_captn,photo_views,photo_rate,photo_rate_count,ctgry_id) values (?,?,?,0,0,0,?)");
			st2.setString(1, fileName);
			st2.setString(2, hash);
			st2.setString(3, caption);
			st2.setInt(4, ctgry_id);
			st2.executeUpdate();
			out.println("</body>");
			out.println("</html>");
			 response.getWriter().print("<br><br><a href='AdminPanel.jsp'> Click here </a> for manual redirect ... !!!");
			response.setHeader("Refresh", "3; URL=AdminPanel.jsp");
		} catch (Exception ex) {
			System.out
					.println("Exception occured in upload servlet JDBC part .. "
							+ ex);
		} finally {
			try {
				rs.close();
				st.close();
				st2.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}

	public void imgResize(File file, String output) throws IOException {
		BufferedImage in = ImageIO.read(file);
		BufferedImage out = null;
		
	//	ImprovedMultistepRescaleOp resample = new ImprovedMultistepRescaleOp(DimensionConstrain.createMaxDimension(2048, 2048, true));
	//	resample.setUnsharpenMask(UnsharpenMask.Normal);
	//	out = resample.filter(in, null);
		if (in.getWidth()<2048 && in.getHeight()<2048)
		{
			out = Resize.resize(in, in.getWidth(), in.getHeight());
		}
		else if (in.getWidth() > in.getHeight()) {
			out = Resize.resize(in, 2048, 2048 * in.getHeight() / in.getWidth());
		} 
		else{
			out = Resize.resize(in, 2048 * in.getWidth() / in.getHeight(), 2048);
		}
		ImageIO.write(out, "jpg", new File(output));
	}

	public void thumbResize(File file, String output) throws IOException {
		BufferedImage in = ImageIO.read(file);
		BufferedImage out = null;
		if (in.getWidth() > in.getHeight()) {
			out = Resize.resize(in, 200 * in.getWidth() / in.getHeight(), 200);
		} else
			out = Resize.resize(in, 200, 200 * in.getHeight() / in.getWidth());
		ImageIO.write(out, "jpg", new File(output));
	}
}