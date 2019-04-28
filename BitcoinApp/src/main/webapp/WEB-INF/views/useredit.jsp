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

        <form action="${contextPath}/admin/user" method="POST">          
            <input type="hidden" value="" name="option" />
            <input type="submit" value="Back"/>
        </form>
     
        <h1>Please Enter the Details Below</h1>
        <form action="${contextPath}/admin/user/edit/update" method="POST">
            <label>username :</label>  <input type="text" name="userName" value="${user.userName}" required><br /><br/>
            <label>password :</label>  <input type="text" name="password" value="${user.password}" required><br /><br/>
            <label>name :</label>  <input type="text" name="name" value="${user.name}" required><br /><br/>
            <label>email :</label>  <input type="text" name="email" value="${user.email}" required><br /><br/>
            <label>balance :</label>  <input type="number" name="balance" value="${user.balance}" required><br /><br/>
            
            <input type="hidden" value="${user.userId}" name="user" />
            <input type="submit" value="Edit"/>
        </form>
        <br>     

</body>
</html>