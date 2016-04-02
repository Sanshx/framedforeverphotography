package com.ankit.db;
import java.sql.*;


import javax.naming.Context;
import javax.naming.InitialContext;

public class ConnectionProvider {

	static Connection con;
	public static Connection getDbConnection() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Context env = (Context)new InitialContext().lookup("java:comp/env");
		String db_host = (String)env.lookup("host_db");
		String db_name = (String)env.lookup("name_db");
		String db_user = (String)env.lookup("user_db");
		String db_pass = (String)env.lookup("pass_db");
		con=DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name,db_user,db_pass);
		} catch(Exception e) {
			System.out.println("Exception occured during connecting to database");
		}
		return con;
	}

}