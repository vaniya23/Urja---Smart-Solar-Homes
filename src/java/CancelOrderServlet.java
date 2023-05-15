import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import connection.DbCon;
import dao.OrderDao;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try(PrintWriter out = response.getWriter()) 
        {
            String id = request.getParameter("id");
            if(id != null) 
            {
                    OrderDao orderDao = new OrderDao(DbCon.getConnection());
                    orderDao.cancelOrder(Integer.parseInt(id));
            }
            response.sendRedirect("orders.jsp");
        } 
        catch (ClassNotFoundException|SQLException e) 
        {
                e.printStackTrace();
        } 
    }
}
