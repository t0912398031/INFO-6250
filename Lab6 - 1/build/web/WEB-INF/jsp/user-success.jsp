<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success page</title>
    </head>

    <body>
       <h1>Welcome ${sessionScope.USER} to Course!</h1>
       <a href="addcourse.htm?formtype=addcourse"><h3>[Add Course]</h3></a>
       <form action="search.htm" method="post">
           <label>Course Name</label><input type="radio" name="courseType" value="courseName"/>
           <label>CRN Name</label><input type="radio" name="courseType" value="courseCRN"/><br>
           <input type="text" name="searchBar"/>
           <input type="submit" name="search" value="Search"/>
       </form>    
    </body>
</html>
