import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) 
        {
            if(request.getSession().getAttribute("auth")!=null) 
            {
                    request.getSession().removeAttribute("auth");
                    response.sendRedirect("index.html");
                    
                     HttpSession session = request.getSession();
                     session.invalidate();
            }
            else 
            {
                    response.sendRedirect("shop.jsp");
            }
        } 
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request, response);
    }

}
