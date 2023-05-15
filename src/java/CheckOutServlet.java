import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import connection.DbCon;
import dao.OrderDao;
import model.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try(PrintWriter out = response.getWriter())
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            
            User auth = (User) request.getSession().getAttribute("auth");

            if(cart_list != null)   
            {
                for(Cart c:cart_list) 
                {
                    Order order = new Order();
                    order.setUname(auth.getName());
                    order.setUphone(auth.getPhone());
                    order.setUemail(auth.getEmail());
                    order.setId(c.getId());
                    order.setQunatity(c.getQuantity());
                    order.setDate(formatter.format(date));

                    OrderDao oDao = new OrderDao(DbCon.getConnection());
                    boolean result = oDao.insertOrder(order);
                    if(!result) break;
                }
                cart_list.clear();
                response.sendRedirect("orders.jsp");
            }
            else 
            {
                if(auth==null) 
                {
                    response.sendRedirect("index.html");
                }
                
                response.sendRedirect("cart.jsp");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }

}
