<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title> </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="part.htm" method="POST">          
            <input type="hidden" value="" name="option" />
            <input type="submit" value="Home"/>
        </form>
        
        
        <h1>Please Enter the Details Below</h1>
        <form:form commandName="movie" method="post">
            <label> title :  </label><form:input path="title" type="text" /><br><br>
            <label> actor :  </label><form:input path="actor" type="text" /><br><br>
            <label> actress :  </label><form:input path="actress" type="text"/><br><br>
            <label> genre :  </label><form:input path="genre" type="text" /><br><br>
            <label> year :  </label><form:input path="year" type="number" />
            <c:if test="${requestScope.validate != null}">
                Please enter valid year
            </c:if> 
            <br><br>
            <!--<input type="hidden" path="login" value="login" name="option" />-->
            <input type="submit"  value="Add"/>
        </form:form>
        
<!--            <form action="movie.htm" method="POST">
            <label>title :</label>  <input type="text" name="title" required><br /><br/>
            <label>actor :</label>  <input type="text" name="actor" required><br /><br/>
            <label>actress :</label>  <input type="text" name="actress" required><br /><br/>
            <label>genre :</label>  <input type="text" name="genre" required><br /><br/>
            <label>year :</label>  <input type="number" name="year" placeholder="ex. 2019" required><br /><br/>
            <input type="hidden" value="insert" name="option" />
            <input type="submit" value="Add Movie"/>
        </form>-->

    </body>
</html>
