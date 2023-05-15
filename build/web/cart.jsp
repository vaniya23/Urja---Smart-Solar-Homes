<%@page import="connection.DbCon"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%--<%@page import="java.text.DecimalFormat"%>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) 
{
    request.setAttribute("person", auth);
}else {
    response.sendRedirect("login.jsp");
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) 
{
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	int total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("total", total);
	request.setAttribute("cart_list", cart_list);
}

%>

<!DOCTYPE html>
<html>
    <head>

    <%@include file="head.jsp"%>
    <title>Shopping Cart</title>
    
    <style type="text/css">
        *{
                font-family: 'Poppins', sans-serif;
        }
            
        body{
                background-color: #EEEEEE;
        }
            
        .table tbody td
        {
            vertical-align: middle;
        }
    
        .btn-incre, .btn-decre
        {
            box-shadow: none;
            font-size: 25px;
        }

        .btn-buy{
            background-color: #3E7C17;
            color: white;
        }
        
        .btn-buy:hover{
            color: #fff2CC;
        }
        
        .fa-minus-square{
            color: #3E7C17;
        }
        
        .fa-plus-square{
            color: #3E7C17;
        }
        
        .btn-checkOut{
            background-color: #3E7C17;
            color: white;
        }
        
        .btn-checkOut:hover{
            color: #fff2CC;
        }
        
        .btn-remove{
            background-color: #C84B31;
            color: white;
        }
        
        .btn-remove:hover{
            background-color: #A20A0A;
            color: white;
        }
    </style>
    
    </head>

    <body>
	<%@include file="navbar.jsp"%>

            <div class="container my-3">
                
		<div class="d-flex py-3"><h3>Total Price: &#x20B9; ${(total>0)?(total):0} </h3> <a class="mx-3 btn btn-checkOut" href="CheckOutServlet">Check Out</a></div>
                
		<table class="table table-light">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
<!--                            <th scope="col">Category</th>-->
                            <th scope="col">Price</th>
                            <th scope="col">Buy Now</th>
                            <th scope="col">Cancel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        if (cart_list != null) 
                        {
                            for (Cart c : cartProduct) 
                            {
                        %>
                        <tr>
                            <td><%=c.getName()%></td>
                            <td><%= c.getPrice()%></td>
                            
                            <td>
                                <form action="OrderNowServlet" method="post" class="form-inline">
                                    <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                                    
                                    <div class="form-group d-flex justify-content-between">
                                        <a class="btn bnt-sm btn-incre" href="QuantityIncDec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a> 
                                        <input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
                                        <a class="btn btn-sm btn-decre" href="QuantityIncDec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
                                    </div>
                                        <button type="submit" class="btn btn-buy btn-sm">Buy</button>
                                </form>
                            </td>
                            
                            <td><a href="RemoveFromCartServlet?id=<%=c.getId() %>" class="btn btn-sm btn-remove">Remove</a></td>
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