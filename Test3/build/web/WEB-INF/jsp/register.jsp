<%-- 
/**
 *
 * @author Chung-Yang Li
 */
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<jsp:useBean id="USER" type="com.neu.edu.pojo.Login" scope="session"></jsp:useBean>--%>
<%--<jsp:useBean id="usersMessages" class="Message" scope="request"></jsp:useBean>--%>
    <!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register View</title>
    </head>
    
    <body>
        <!--<a href='user.htm'>Home</a><br/>-->
        <form action="auth.htm" method="POST">          
            <input type="hidden" value="" name="option" />
            <input type="submit" value="Home"/>
        </form>
     
        <h1>Please Enter the Details Below</h1>
        <form action="user.htm" method="POST">
            <label>username :</label>  <input type="text" name="username" required><br /><br/>
            <label>password :</label>  <input type="text" name="password" required><br /><br/>
            <label>name :</label>  <input type="text" name="name" required><br /><br/>
            <label>balance :</label>  <input type="number" name="balance" required><br /><br/>
            
            <input type="hidden" value="addUser" name="userOption" />
            <input type="submit" value="Register"/>
        </form>
        <br>
        
        <c:if test="${requestScope.status != null}">
            <c:out value="${requestScope.status}"/>

            <form action="movie.htm" method="POST">
                <input type="hidden" value="browse" name="option" />
                <input type="submit" value="Browse Movies"/>
            </form>
            
        </c:if> 

</body>
</html>