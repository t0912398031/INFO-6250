<%-- 
/**
 *
 * @author Chung-Yang Li
 */
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register View</title>
    </head>
    
    <body>
    	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

        <form action="${contextPath}/" method="POST">          
            <input type="hidden" value="" name="option" />
            <input type="submit" value="Home"/>
        </form>
     
        <h1>Please Enter the Details Below</h1>
        <form action="${contextPath}/register/" method="POST">
            <label>username :</label>  <input type="text" name="userName" required><br /><br/>
            <label>password :</label>  <input type="text" name="password" required><br /><br/>
            <label>name :</label>  <input type="text" name="name" required><br /><br/>
            <label>balance :</label>  <input type="number" name="balance" required><br /><br/>
            <label>email :</label>  <input type="text" name="email" required><br /><br/>
            
            <input type="hidden" value="addUser" name="userOption" />
            <input type="submit" value="Register"/>
        </form>
        <br>     

</body>
</html>