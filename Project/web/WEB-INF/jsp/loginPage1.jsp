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

        <h1>HW5</h1>
        
        <form action="part.htm" method="POST">          
            <input type="hidden" value="movie" name="option" />
            <input type="submit" value="Movie"/>
        </form>
        <br>
        <form action="part.htm" method="POST">          
            <input type="hidden" value="quiz" name="option" />
            <input type="submit" value="Quiz"/>
        </form>
        <br>
        <form action="part.htm" method="POST">          
            <input type="hidden" value="form" name="option" />
            <input type="submit" value="Form"/>
        </form>
    </body>
</html>
