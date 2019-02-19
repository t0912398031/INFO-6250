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
        <a href='user.htm'>Home</a><br/>
     
        <h1>Please Enter the Details Below</h1>
        <form action="movie.htm" method="POST">
            <label>title :</label>  <input type="text" name="title" required><br /><br/>
            <label>actor :</label>  <input type="text" name="actor" required><br /><br/>
            <label>actress :</label>  <input type="text" name="actress" required><br /><br/>
            <label>genre :</label>  <input type="text" name="genre" required><br /><br/>
            <label>year :</label>  <input type="number" name="year" placeholder="ex. 2019" required><br /><br/>
            <input type="hidden" value="insert" name="option" />
            <input type="submit" value="Add Movie"/>
        </form>
        <br>
        
        <c:if test="${requestScope.status != null}">
            <c:out value="${requestScope.status}"/>

            <form action="movie" method="POST">
                <input type="hidden" value="browse" name="option" />
                <input type="submit" value="Browse Movies"/>
            </form>
            
        </c:if> 

</body>
</html>