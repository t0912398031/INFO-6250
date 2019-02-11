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
        <title>Movie</title>
    </head>
    
    <body>
        <a href='index.jsp'>Home</a><br/><br/>
        
        <h1>Welcome to Our Movie Store</h1>
        <form action="movie" method="POST">
            <select name="option">
            <option value="browse">Browse Movies</option>
            <option value="add">Add New Movie to Database</option>
            </select>
            <!--<input type="hidden" value="login" name="option" />-->
            <input type="submit" value="Send"/>
        </form>
        
        <br><br>
        
        
        <c:if test="${requestScope.movies != null}"> 
            <c:set var="movies" value="${requestScope.movies}" scope="request"/> 
            <%--<c:set var="size" value="${requestScope.size}" scope="request"/>--%>
        <%--<c:out value="${rs.next()}"/>--%>
        <%--<c:out value="${size}"/>--%>
        
        <table border="1">
            <thead>  
                <th>Title</th>
                <th>Actor</th>
                <th>Actress</th>
                <th>Genre</th>
                <th>Year</th> 
            </thead>
            <tbody>           
            <c:forEach var="m" items="${movies}">
                <tr>
                    <td>${m.getTitle()}</td>
                    <td>${m.getActor()}</td>
                    <td>${m.getActress()}</td>
                    <td>${m.getGenre()}</td>
                    <td>${m.getYear()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    
        </c:if> 

</body>
</html>