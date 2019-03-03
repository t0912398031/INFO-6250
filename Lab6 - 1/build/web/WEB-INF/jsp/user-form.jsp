<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>

    <body>
        <form:form commandName="user" method="post">
            <label>Username:</label><form:input path="username" type="text"/>
            <label>Password</label><form:input path="password" type="text"/>
            <input type="hidden" value="login" name="option"/>
            <input type="submit" value="login"/>
        </form:form>
    </body>
</html>
