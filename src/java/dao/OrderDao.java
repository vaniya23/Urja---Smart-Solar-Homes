package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.*;

public class OrderDao 
{
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public OrderDao(Connection con) 
    {
        super();
        this.con = con;
    }

    public boolean insertOrder(Order model) 
    {
        boolean result = false;
        try 
        {
            query = "insert into orders (uname, uphone, uemail, product_id, quantity, date) values(?,?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, model.getUname());
            pst.setString(2, model.getUphone());
            pst.setString(3, model.getUemail());
            pst.setInt(4, model.getId());
            pst.setInt(5, model.getQunatity());
            pst.setString(6, model.getDate());
            pst.executeUpdate();
            result = true;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public List<Order> userOrders(String uname, String uphone, String uemail) 
    {
        List<Order> list = new ArrayList<>();
        try 
        {
            query = "select * from orders where uname=? and uphone=? and uemail=? order by orders.order_id desc";
            pst = this.con.prepareStatement(query);
            pst.setString(1, uname);
            pst.setString(2, uphone);
            pst.setString(3, uemail);
            rs = pst.executeQuery();
            
            while (rs.next()) 
            {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("product_id");
                Product product = productDao.getSingleProduct(pId);
                
                order.setOrderId(rs.getInt("order_id"));
                order.setId(pId);
                order.setName(product.getName());
//                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("quantity"));
                order.setQunatity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                list.add(order);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void cancelOrder(int id) 
    {
        //boolean result = false;
        try 
        {
            query = "delete from orders where order_id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
    
}
