package com.onlineshopping.order;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class OrderDAO {
	 private ResultSet rs;
	public static Connection getConnection(){  
        Connection conn=null;  
        
        try{  
        	Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb","root","Renuka#123");
        }catch(Exception e){System.out.println(e);}  
        return conn;  
    }  
	
		public boolean insertOrder(Order model) {
        boolean result = false;
        try {
        	Connection conn=OrderDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
            		"insert into orders (i_id, item_name, u_id, category_id, o_quantity, item_price, o_date) values(?,?,?,?,?,?,?)");
            ps.setInt(1, model.getId());
            ps.setString(2, model.getName());
            ps.setInt(3, model.getUid());
            ps.setString(4, model.getCategoryid());
            ps.setInt(5, model.getQuantity());
            ps.setDouble(6, model.getPrice());
            ps.setString(7, model.getDate());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
        	Connection conn=OrderDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
            		"select * from orders where u_id=? order by orders.o_id desc");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                int iId = rs.getInt("i_id");
                order.setOrderId(rs.getInt("o_id"));
                order.setId(iId);
                order.setName(rs.getString("item_name"));
                order.setCategoryid(rs.getString("category_id"));
                order.setPrice(rs.getDouble("item_price"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        //boolean result = false;
        try {
        	Connection conn=OrderDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
            		"delete from orders where o_id=?");
            ps.setInt(1, id);
            ps.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
