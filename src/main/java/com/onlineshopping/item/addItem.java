package com.onlineshopping.item;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class additemDAO
 */
@WebServlet("/addItem")
public class addItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     response.setContentType("text/html");  
	     PrintWriter out=response.getWriter();  
          
	     	String name = request.getParameter("name");
			System.out.println(name);
			String categoryid = request.getParameter("category");
			System.out.println(categoryid);
			String limitperorder = request.getParameter("limit_per_order");
			String iprice = request.getParameter("price"); 
			Double price=Double.parseDouble(iprice);
	        Item item=new Item();  
	        item.setName(name);
	        item.setCategoryid(categoryid);
	        item.setLimitperorder(limitperorder);
	        item.setPrice(price);
	          System.out.print(item);
	        int status=itemDAO.save(item);  
	        if(status>0){  
	            out.print("<p>Record saved successfully!</p>");  
	            request.getRequestDispatcher("additem.jsp").include(request, response);  
	        }else{  
	            out.println("Sorry! unable to save record");  
	        }  
	          
	        out.close();  
   
	}
	

}
