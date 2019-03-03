<%-- 
    Document   : addCourse
    Created on : 2019/2/23, 上午 11:57:42
    Author     : Chung-Yang Li
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add New Course</h1>
        
        <form action="addcourse.htm" method="post">
            Course Name<input type="text" name="coursename"/><br>
            Course CRN<input type="text" name="courseCRN"/><br>
            <input type="hidden" value="submitcourse" name="formtype"/>
            <input type="submit" value="Add Course" name="addcourse"/>
        </form>
    </body>
</html>
