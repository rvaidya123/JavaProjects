package com.onlineshopping.item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onlineshopping.order.CartItem;

public class itemDAO extends CartItem{

    public static Connection getConnection(){  
        Connection conn=null;  
        try{  
        	Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb","root","Renuka#123");
        }catch(Exception e){System.out.println(e);}  
        return conn;  
    }  
    
    public static int save(Item i){  
        int status=0;  
        try{  
            Connection conn=itemDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
            		"insert into item(name,category_id,limit_per_order,price) values(?,?,?,?)");
            ps.setString(1,i.getName());  
            ps.setString(2,i.getCategoryid());  
            ps.setString(3,i.getLimitperorder());  
            ps.setDouble(4,i.getPrice());  
              
            status=ps.executeUpdate();  
              
            conn.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    public static int update(Item i){  
        int status=0;  
        try{  
            Connection conn=itemDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
            		"update item set name=?,limit_per_order=?,price=? where id=?");
            ps.setString(1,i.getName());  
            ps.setString(2,i.getLimitperorder());  
            ps.setDouble(3,i.getPrice());  
            ps.setInt(4, i.getId());
              
            status=ps.executeUpdate();  
              
            conn.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    public static int delete(int id){  
        int status=0;  
        try{  
            Connection conn=itemDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement("delete from item where id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            conn.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    public static Item getItemById(int id){  
        Item i=new Item();  
        try{  
            Connection conn=itemDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement("select * from item where id=?");  
            ps.setInt(1, id); 
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
            	i.setId(rs.getInt(1));
                i.setName(rs.getString(2));  
                i.setCategoryid(rs.getString(3));
                i.setLimitperorder(rs.getString(4));  
                i.setPrice(rs.getDouble(5));  
            }  
            conn.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return i;  
    }  
    public static List<Item> getAllItems(){  
        List<Item> list=new ArrayList<Item>();  
          
        try{  
            Connection con=itemDAO.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from item");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Item i=new Item();   
            	i.setId(rs.getInt(1));
                i.setName(rs.getString(2)); 
                i.setCategoryid(rs.getString(3));
                i.setLimitperorder(rs.getString(4));  
                i.setPrice(rs.getDouble(5)); 
               
                System.out.println(list.add(i));
            }  
          
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    } 
    
	public double getTotalCartPrice(ArrayList<CartItem> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (CartItem item : cartList) {
                	Connection con=itemDAO.getConnection();  
                    PreparedStatement ps=con.prepareStatement("select price from item where id=?");  
                    ps.setInt(1, item.getId());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    
    public List<CartItem> getCartItems(ArrayList<CartItem> cartList) {
        List<CartItem> i = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (CartItem item : cartList) {
                	Connection con=itemDAO.getConnection();  
                    PreparedStatement ps=con.prepareStatement("select * from item where id=?");  
                    ps.setInt(1, item.getId());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        CartItem row = new CartItem();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategoryid(rs.getString("category_id"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        i.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return i;
    }

}
