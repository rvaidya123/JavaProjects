package com.onlineshopping.order;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.onlineshopping.registration.*;




@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                String productName = request.getParameter("name");
                String cateId =request.getParameter("categoryid");
                String iPrice =request.getParameter("price");
                if (productQuantity <= 0) {
                	productQuantity = 1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setName(productName);
                orderModel.setUid(auth.getId());
                orderModel.setCategoryid(cateId);
                orderModel.setQuantity(productQuantity);
                orderModel.setPrice(Double.parseDouble(iPrice));
                orderModel.setDate(formatter.format(date));
                OrderDAO orderDao = new OrderDAO();
                boolean result = orderDao.insertOrder(orderModel);
                if (result) {
                    ArrayList<CartItem> cart_list = (ArrayList<CartItem>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (CartItem c : cart_list) {
                            if (c.getId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect("orders.jsp");
                } else {
                    out.println("order failed");
                }
            } else {
                response.sendRedirect("home.jsp");
            }

        } 
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}