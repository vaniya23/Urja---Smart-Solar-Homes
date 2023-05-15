<%@page import="model.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login Form</title>
        <link rel="stylesheet" type="text/css" href="register.css">
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>  <!-- for ion-icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    </head>
    
    <body>
        <div class="wrapper">

            <div class="r_form_wrap">
                
                <div class="title">
                    <p>Login Form</p>
                </div>

                <div class="r_form">

                    <form action="logInServlet" method="post">
                        
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

                        <div class="input_wrap">
                            <label for="name">Name</label>  
                            <div class="input_item">
                                <span class="icon">
                                    <ion-icon name="person-sharp"></ion-icon>
                                </span>
                                <input type="text" name="uname" class="input" id="uname" required>
                            </div>
                        </div>
                        
                        <div class="input_wrap">
                            <label for="phone">Phone No.</label>
                            <div class="input_item">
                                <span class="icon">
                                    <ion-icon name="call-sharp"></ion-icon>
                                </span>
                                <input type="phone" name="uphone" class="input" id="uphone" required>
                            </div>
                        </div>

                        <div class="input_wrap">
                            <label for="email">Email Address</label>
                            <div class="input_item">
                                <span class="icon">
                                    <ion-icon name="mail-sharp"></ion-icon>
                                </span>
                                <input type="email" name="uemail" class="input" id="uemail" required>
                            </div>
                        </div>

                        <div class="input_wrap">
                            <label for="password">Password</label>
                            <div class="input_item">
                                <span class="icon">
                                    <ion-icon name="key-sharp"></ion-icon>
                                </span>
                                <input type="password" name="upassword" class="input" id="upassword" required>
                            </div>
                        </div>

                        <input type="submit" value="Login" name="register" id="register">

                    </form>

                </div>

            </div>
        </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.min.js" integrity="sha384-heAjqF+bCxXpCWLa6Zhcp4fu20XoNIA98ecBC1YkdXhszjoejr5y9Q77hIrv8R9i" crossorigin="anonymous"></script>
    </body>
  
</html>
