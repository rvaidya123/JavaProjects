
package com.onlineshopping.order;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DbCon;
import com.onlineshopping.registration.User;



@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<CartItem> cart_list = (ArrayList<CartItem>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			if(cart_list != null && auth!=null) {
				for(CartItem c:cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					//order.setName(c.getName());
					//order.setCategoryid(c.getCategoryid());
					order.setQuantity(c.getQuantity());
					//order.setPrice(c.getPrice());
					order.setDate(formatter.format(date));
					OrderDAO oDao = new OrderDAO();
					oDao.getConnection();
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}else {
				if(auth==null) {
					response.sendRedirect("home.jsp");}
					response.sendRedirect("cart.jsp");}
			} 
	/*	catch (ClassNotFoundException|SQLException e){
				e.printStackTrace();     
				  }   */
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub        
		doGet(request, response);}

}

