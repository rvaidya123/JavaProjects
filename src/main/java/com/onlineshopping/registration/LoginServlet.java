package com.onlineshopping.registration;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DbCon;




@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String usertype = request.getParameter("user_type");
			System.out.println(usertype);

			UserDAO udao = new UserDAO(DbCon.getConnection());
			User user = udao.userLogin(email, password, usertype);
			System.out.println(user);
			if (user != null && usertype.contains("1")) {
				request.getSession().setAttribute("auth", user);
//				System.out.print("user logged in");
				response.sendRedirect("viewItem.jsp");
			} else if(user != null && usertype.contains("2")) {
				response.sendRedirect("viewItemToUpdate.jsp");
			}else {
			    out.print("<div class=\"alert alert-danger\">There is no such Record!</div>");  
				response.sendRedirect("error.jsp");
			}

		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		} 

	}
}
