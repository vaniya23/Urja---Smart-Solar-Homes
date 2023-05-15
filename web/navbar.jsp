<style>
    *{
                font-family: 'Poppins', sans-serif;
    }
        
    .navbar-expand-lg{
        background-color: #125C13;
    }
    
    a{
        color: white;
    }
    
    a:hover{
        color: #fff2CC;
    }
    
    .badge-cart{
        background-color: white;
        color: black;
    }
</style>

<nav class="navbar navbar-expand-lg">
	<div class="container">
		<a class="navbar-brand" href="shop.jsp">Shopping Cart</a>
                
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
                    
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="shop.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-cart">${cart_list.size()}</span> </a></li>
				
				<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
				
<!--				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>-->
				
			</ul>
                        
		</div>
	</div>
</nav>