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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Message;

/**
 *
 * @author Vaniya
 */
public class registerServlet extends HttpServlet 
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
            String state = request.getParameter("ustate");
            String elec_num = request.getParameter("uelec_num");
            String elec_comp = request.getParameter("uelec_comp");
            String password = request.getParameter("upassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/registration?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String username = "root";
            String pass = "1234";
            Connection conn = DriverManager.getConnection(url, username, pass);
            
            PreparedStatement pst1 = conn.prepareStatement("Select name,phone,email from userinfo where name=? and phone=? and email=?");
            pst1.setString(1,name);
            pst1.setString(2, phone);
            pst1.setString(3, email); 
            ResultSet rs = pst1.executeQuery();

            if(rs.next())
            {
                //use of Message class
                Message msg = new Message("User already exists. <br> Please enter the details again.", "error", "alert-danger");
                HttpSession s = request.getSession();
                s.setAttribute("msg", msg);
                response.sendRedirect("register.jsp");
//                PrintWriter out = response.getWriter();
//                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"registerServletStyle.css\" />");
//                out.println("<h1> User <i>" + name + "</i> already exists, please enter the details again. </h1>");
            }
            else
            {
                PreparedStatement pst2 = conn.prepareStatement("insert into userinfo(name, phone, email, state, elec_cons_num, elec_dist_comp, password) values(?,?,?,?,?,?,?)");
                pst2.setString(1, name);
                pst2.setString(2, phone);
                pst2.setString(3, email);
                pst2.setString(4, state);
                pst2.setString(5, elec_num);
                pst2.setString(6, elec_comp);
                pst2.setString(7, password);
                pst2.executeUpdate();
                pst2.close();

//                PrintWriter out = response.getWriter();
//                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"registerServletStyle.css\" />");
//                out.println("<h1> User <i>" + name + "</i> Successfully Registered! </h1>");
//                out.println("<br>");
//                out.println("<h2><a href=\"index.html\"> Click here to Login! </a></h2>");
                
                //use of Message class
                Message msg = new Message("User Successfully Registered! <br> Now you can Login.", "error", "alert-danger");
                HttpSession s = request.getSession();
                s.setAttribute("msg", msg);
                response.sendRedirect("login.jsp");
            }
            
            rs.close();
            pst1.close();
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
