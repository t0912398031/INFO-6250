<%-- 
/**
 *
 * @author Chung-Yang Li
 */
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.neu.edu.pojo.Message"%>
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
        <h2>The following items has been added to your cart successfully:</h2>
        
        <c:forEach var="items" items="${requestScope.items}">
        <c:out value="${items}" />
        </c:forEach> 

    <%--<c:out value="Shop for ${requestScope.option}:" />--%>
    <h2>Your Cart:</h2>
    <div>Books:</div>
    <c:forEach var="items" items="${sessionScope.book}">
        <c:out value="${items}" />
    </c:forEach>
    <br><br>
    <div>Laptops:</div>
    <c:forEach var="items" items="${sessionScope.laptop}">
        <c:out value="${items}" />
    </c:forEach> 
    <br><br>
    <div>CDs:</div>
    <c:forEach var="items" items="${sessionScope.cd}">
        <c:out value="${items}" />
    </c:forEach> 
    <br><br>
    



</body>
</html>