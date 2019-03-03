<%-- 
    Document   : listUsers
    Created on : Feb 1, 2019, 7:52:28 PM
    Author     : Adi
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
        <h2>Courses for ${sessionScope.USER.username}</h2>
        <table>
            <tr>
                <th>Course</th>
                <th>CRN</th>
            </tr>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.courseName}" /></td>
                    <td><c:out value="${course.courseCRN}" /></td>
                </tr>
            </c:forEach> 
        </table><br/>
        <a href="addcourse.htm?formtype=home">GO HOME</a>
    </body>
</body>
</html>
