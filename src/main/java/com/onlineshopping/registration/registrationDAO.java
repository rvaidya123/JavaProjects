package com.onlineshopping.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class registration
 */
@WebServlet("/registrationDAO")
public class registrationDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement prestmt;
	ResultSet rs;
    public void init() {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb","root","Renuka#123");
				prestmt=conn.prepareStatement("insert into user(first_name,last_name,email,password,user_type_id) values(?,?,?,?,?)");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    	 }
	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter("first_name");
		System.out.println(firstname);
		String lastname = request.getParameter("last_name");
		System.out.println(lastname);
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("user_type_id");
		try {
			System.out.println("---------------");
			prestmt.setString(1, firstname);
			prestmt.setString(2, lastname);
			prestmt.setString(3, email);
			prestmt.setString(4, password);
			prestmt.setString(5, usertype);
			System.out.println("1-----------");
			prestmt.executeUpdate();
			prestmt.close();
			System.out.println("2-------");
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		    out.print("<center><h1>User Registered Successfully!</h1></center>");  
		    out.print("<center><a href=\"home.jsp\">Click Here for Login...</a></center>\r\n"
		    		+ "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
   
	}
	public void destroy() {
		try {
			prestmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
