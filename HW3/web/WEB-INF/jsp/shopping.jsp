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
        <title>Shopping View</title>
    </head>
    
    <body>
        <a href='index.jsp'>Home</a><br/><br/>
        
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
    <c:out value="Shop for ${requestScope.option}:" />

    <form action = 'shoppingtype' method = 'post'>
        <c:forEach var="items" items="${requestScope.items}">
            <%--<c:out value="${items}" />--%>
            <input type='checkbox' name="${requestScope.option}" value="${items}" />${items}<br />
        </c:forEach> 
        <input type="hidden" name="option" value="cart"/>
        <input type="hidden" name="type" value="${requestScope.option}"/>
    <input type =  'submit' value = 'Add to Cart' name = 'button'/><br /></p>
    </form>



</body>
</html>