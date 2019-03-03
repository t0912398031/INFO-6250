<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Login Section</h1>
        <form:form commandName="user" method="post">
            <label> Username :  </label><form:input path="username" type="text" />
            <label> Password :  </label><form:input path="password" type="password" />
            <input type="hidden" path="login" value="login" name="option" />
            <input type="submit"  value="Login"/>
        </form:form>
        <br/><br/><br/><br/><br/><br/><br/>
<!--        <h1>Registration Section</h1>
        <form action="user.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />            
            <input type="hidden" value="register" name="option" />
            <input type="submit" value="Sign Up"/>
        </form>-->
    </body>
</html>
