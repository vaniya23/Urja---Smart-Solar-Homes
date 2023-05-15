<%@page import="model.Product"%>
<%@page import="model.Message"%>
<%@page import="connection.DbCon"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
  
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) 
{
    request.setAttribute("person", auth);
}else {
    response.sendRedirect("login.jsp");
}

ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

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
        <title>Shopping Site</title>
        
        <style>
            *{
                font-family: 'Poppins', sans-serif;
                transition: all 0.25s ease-in-out;
            }
            
            body{
                background-color: #EEEEEE;
            }
            
            .btn-addToCart{
                background-color: #fff2CC;
                color: black;
            }
            
            .btn-addToCart:hover{
                background-color: #F4A442;
                color: white;
            }
            
            .btn-buyNow{
                background-color: #3E7C17;
                color: white;
            }
            
            .btn-buyNow:hover{
                background-color: #61B15A;
                color: white;
            }
            
            .card-header{
                background-color: #3E7C17;
                color: white;
            }
            
            .card-img-top:hover{ 
                transform: scale(1.2); 
            }
        </style>
    </head>
    
    <body>
            <%@include file="navbar.jsp"%>
            
            <% 
            Message msg = (Message)session.getAttribute("msg");
            if(msg != null){
            %>

            <div class="alert alert-danger mt-2" role="alert">
                <%= msg.getContent() %>
            </div>

            <%
                }
                session.removeAttribute("msg");
            %>

            <div class="container">
                    <div class="card-header my-3">All Products</div>
                    <div class="row">
                        
                        <%
			if (!products.isEmpty()) 
                        {
				for (Product p : products) 
                                {
			%>
                        
                            <div class="col-md-3 my-3">
                                
                                    <div class="card w-100">
                                            <img class="card-img-top" src="<%=p.getImage() %>" alt="Card image cap">
                                            
                                            <div class="card-body">
                                                    <h5 class="card-title"><%=p.getName() %></h5>
                                                    <h6 class="price">Price:  &#x20B9; <%=p.getPrice() %></h6>
<!--                                                    <h6 class="category">Category: </h6>-->
                                                    
                                                    <div class="mt-3 d-flex justify-content-between">
                                                            <a class="btn btn-addToCart" href="AddToCartServlet?id=<%=p.getId()%>">Add to Cart</a> 
                                                            <a class="btn btn-buyNow" href="OrderNowServlet?quantity=1&id=<%=p.getId()%>">Buy Now</a>            
                                                    </div>
                                            </div>
                                                    
                                    </div>
                            </div>
                        <%
                                }
                        } 
                        else 
                        {
                            out.println("There is no proucts");
                        }
                        %>

                    </div>
            </div>

            <%@include file="footer.jsp"%>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.min.js" integrity="sha384-heAjqF+bCxXpCWLa6Zhcp4fu20XoNIA98ecBC1YkdXhszjoejr5y9Q77hIrv8R9i" crossorigin="anonymous"></script>

    </body>
</html>