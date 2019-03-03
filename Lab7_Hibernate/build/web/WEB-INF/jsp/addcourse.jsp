<%-- 
    Document   : addcourse
    Created on : Feb 22, 2019, 9:49:19 PM
    Author     : Adi
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
            Course Name<input type="text" name="coursename" /><br/>
            Course CRN<input type="text" name="courseCRN" /><br/><br/>
            <input type="hidden" value="submitcourse" name="formtype" />
            <input type="submit" value="Add Course" name="addcourse" />
        </form>
    </body>
</html>
