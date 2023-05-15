/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import model.Message;

/**
 *
 * @author Vaniya
 */
public class logInServlet extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try  
        {
            String name = request.getParameter("uname");
            String phone= request.getParameter("uphone");
            String email = request.getParameter("uemail");
            String password = request.getParameter("upassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/registration?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String username = "root";
            String pass = "1234";
            Connection conn = DriverManager.getConnection(url, username, pass);
            
            PreparedStatement pst = conn.prepareStatement("Select password from userinfo where name=? and phone=? and email=?");
            pst.setString(1,name);
            pst.setString(2, phone);
            pst.setString(3, email); 
            ResultSet rs = pst.executeQuery();
            
            if(rs.next() && rs.getString("password").equals(password))
            {
//                PrintWriter out = response.getWriter();
//                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"registerServletStyle.css\" />");
//                out.println("<h1> Welcome <i>" + name + "</i> to our portal. </h1>");
//                out.println("<br>");
//                out.println("<h2><a href=\"shop.jsp\"> Click here to know more. </a></h2>");
                
                User user = new User(name, phone, email);
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("shop.jsp");
            }
            else
            {
//                PrintWriter out = response.getWriter();
//                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"registerServletStyle.css\" />");
//                out.println("<h1> User not Registered or Wrong password for user <i>" + name + "</i> entered. </h1>");
                
                //use of Message class
                Message msg = new Message("User not Registered or Wrong password entered.", "error", "alert-danger");
                HttpSession s = request.getSession();
                s.setAttribute("msg", msg);
                response.sendRedirect("login.jsp");
            }
            rs.close();
            pst.close();
            conn.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
