package com.onlineshopping.stock;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addStock
 */
@WebServlet("/addStock")
public class AddStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");  
	     PrintWriter out=response.getWriter();  
	     String Id = request.getParameter("item_id");
		 System.out.println(Id);
		String quantity = request.getParameter("quantity");
		System.out.println(quantity);
	    Stock stockItem=new Stock();
	    stockItem.setItemid(Id);
	    stockItem.setQuantity(quantity);
	    int status=StockDAO.save(stockItem);
	    if(status>0){  
            out.print("<div class=\"alert alert-success\">Record saved successfully!</div>");  
            request.getRequestDispatcher("addStock.jsp").include(request, response);  
        }else{  
            out.println("<div class=\"alert alert-danger\">Sorry! unable to save record");  
        }  
          
        out.close(); 
	}

	
}
