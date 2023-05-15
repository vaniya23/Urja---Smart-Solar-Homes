<%--<%@page import="java.text.DecimalFormat"%>--%>
<%@page import="dao.OrderDao"%>
<%@page import="connection.DbCon"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    User auth = (User) request.getSession().getAttribute("auth");
    
    List<Order> orders = null;
    if (auth != null) 
    {
        request.setAttribute("person", auth);
        OrderDao orderDao  = new OrderDao(DbCon.getConnection());
        orders = orderDao.userOrders(auth.getName(), auth.getPhone(), auth.getEmail());
    }
    else
    {
        response.sendRedirect("index.html");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) 
    {
        request.setAttribute("cart_list", cart_list);
    }
	
%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
        <title>Orders</title>
        
        <style>
            *{
                font-family: 'Poppins', sans-serif;
            }
        
            body{
                background-color: #EEEEEE;
            }
            
            .card-header{
                background-color: #3E7C17;
                color: white;
            }
            
            .btn-cancel{
                background-color: #C84B31;
                color: white;
            }
            
            .btn-cancel:hover{
                 background-color: #A20A0A;
            color: white;
            }
        </style>
    </head>
    
    <body>
        <%@include file="navbar.jsp"%>
        
        <div class="container">
            <div class="card-header my-3">All Orders</div>
            
            <table class="table table-light">
                <thead>
                    <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Name</th>
<!--                            <th scope="col">Category</th>-->
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Cancel</th>
                    </tr>
                </thead>
                
                <tbody>

                <%
                if(orders != null)
                {
                    for(Order o:orders)
                    {
                %>
                    <tr>
                        <td><%=o.getDate() %></td>
                        <td><%=o.getName() %></td>                   
                        <td><%=o.getQunatity() %></td>
                        <td><%=o.getPrice() %></td>
                        <td><a class="btn btn-sm btn-cancel" href="CancelOrderServlet?id=<%=o.getOrderId()%>">Cancel Order</a></td>
                    </tr>
                <%
                    }
                }
                %>

                </tbody>
            </table>
                
        </div>
                        
        <%@include file="footer.jsp"%>
        
    </body>
    
</html>