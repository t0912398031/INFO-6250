<%-- 
    Document   : user-success
    Created on : Feb 20, 2019, 5:12:17 PM
    Author     : adike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome ${sessionScope.USER} to Courses!</h1>
        <a href="addcourse.htm?formtype=addcourse"><h3>[Add Courses]</h3></a>
        <h2>Search</h2>
        <form action="search.htm" method="post">
            <label>Course Name</label><input type="radio" name="courseType" value="courseName" />
            <label>CRN Name</label><input type="radio" name="courseType" value="courseCRN" /><br/>
            <input type="text" name="searchBar" />
            <input type="submit" name="search" value="Search" />
        </form>
    </body>
</html>
