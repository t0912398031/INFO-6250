<%-- 
    Document   : user-success
    Created on : Feb 20, 2019, 5:12:17 PM
    Author     : adike
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Successful</h1>
<!--        <a href="addcourse.htm?formtype=addcourse"><h3>[Add Courses]</h3></a>
        <h2>Search</h2>
        <form action="search.htm" method="post">
            <label>Course Name</label><input type="radio" name="courseType" value="courseName" />
            <label>CRN Name</label><input type="radio" name="courseType" value="courseCRN" /><br/>
            <input type="text" name="searchBar" />
            <input type="submit" name="search" value="Search" />
        </form>-->
        
        <%--<c:if test="${sessionScope.Movie != null}">--%> 
            <%--<c:set var="movies" value="${sessionScope.Movie}" scope="request"/>--%> 
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
                    <td>${m.title}</td>
                    <td>${m.actor}</td>
                    <td>${m.actress}</td>
                    <td>${m.genre}</td>
                    <td>${m.year}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    
        <%--</c:if>--%>
    </body>
</html>
