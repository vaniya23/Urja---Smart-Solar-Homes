import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import model.Message;
import model.*;

@WebServlet(name = "AddToCartServlet", urlPatterns = "add-to-cart")
public class AddToCartServlet extends HttpServlet 
{ 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {   
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) 
        {
            ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cart_list == null)
            {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("shop.jsp");
            } 
            else 
            {
                cartList = cart_list;

                boolean exist = false;
                for (Cart c : cart_list) 
                {
                    if (c.getId() == id) 
                    {
                        exist = true;
                        Message msg = new Message("Item Already Present In Cart. Go To Cart!", "error", "alert-danger");
                        HttpSession s = request.getSession();
                        s.setAttribute("msg", msg);
                        
                        response.sendRedirect("shop.jsp");
                    }
                }

                if (!exist) 
                {
                    cartList.add(cm);
                    response.sendRedirect("shop.jsp");
                }
            }   
        }
    }
}
