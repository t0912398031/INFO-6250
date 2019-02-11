<%-- 
 /**
 *
 * @author Chung-Yang Li
 */
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.neu.edu.pojo.Row"%>
<%--<jsp:useBean id="USER" type="com.neu.edu.pojo.Login" scope="session"></jsp:useBean>--%>
<%--<jsp:useBean id="usersMessages" class="Message" scope="request"></jsp:useBean>--%>
    <!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSV</title>
    </head>
    
    <body>
        
        <h1>Please enter csv folder name</h1>
        <form action = 'csv' method = 'post'>
        <input type="text" name="foldername">
        <input type = 'submit' value = 'Submit' name = 'button'/>
        </form>
        
        
        <c:if test="${requestScope.resultSet != null}"> 
            <c:set var="rs" value="${requestScope.resultSet}" scope="request"/> 
            <c:set var="size" value="${requestScope.size}" scope="request"/>
        <%--<c:out value="${rs.next()}"/>--%>
        <%--<c:out value="${size}"/>--%>
        <c:forEach var="r" items="${requestScope.rows}">
            <%--<c:out value="${r}"/>--%>
            <c:out value="${r.getString1}"/>
            <%--<c:out value="${r.getString2}"/>--%>
   
        </c:forEach>
        
        
        
           
            
            <%--<c:out value="${'random number <= 7, if > 7 redirect to Google'}"/>--%>        
        </c:if>  
<!--        
        <form action="shoppingtype" method ="post">
            <input type="hidden" name="option" value="book"/>
            <input type="submit" value="Book"/>
        </form><br/>
        <form action="shoppingtype" method ="post">
            <input type="hidden" name="option" value="laptop"/>
            <input type="submit" value="Laptop"/>
        </form><br/>
        <form action="shoppingtype" method ="post">
            <input type="hidden" name="option" value="cd"/>
            <input type="submit" value="CD"/>
        </form><br/>
        
        -->
        
<!--        <form action="login" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        <h4>SEARCH USERS</h4>
        <form action="user" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>-->
    



</body>
</html>