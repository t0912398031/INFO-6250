<%-- 
    Document   : login
    Created on : Feb 14, 2019, 5:47:49 PM
    Author     : harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
<!--        <h1>Login Section</h1>
        <form action="authentication.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />
            <input type="hidden" value="login" name="option" />
            <input type="submit" value="Login"/>
        </form>
        <br/><br/><br/><br/><br/><br/><br/>
        <h1>Registration Section</h1>
        <form action="authentication.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />            
            <input type="hidden" value="register" name="option" />
            <input type="submit" value="Sign Up"/>
        </form>-->
        
        
        
        <h1>HW5</h1>
        
        <form action="part.htm" method="POST">          
            <input type="hidden" value="csv" name="option" />
            <input type="submit" value="CSV"/>
        </form>
        <br>
        <form action="part.htm" method="POST">          
            <input type="hidden" value="movie" name="option" />
            <input type="submit" value="Movie"/>
        </form>
        <br>
        <form action="part.htm" method="POST">          
            <input type="hidden" value="quiz" name="option" />
            <input type="submit" value="Quiz"/>
        </form>
        
        <form action="part.htm" method="POST">          
            <input type="hidden" value="form" name="option" />
            <input type="submit" value="Form"/>
        </form>
    </body>
</html>
