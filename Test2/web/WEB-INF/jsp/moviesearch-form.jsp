<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<br><br>
    
        <form:form commandName="moviesearch" method="post">           
            <label> Search by ID :  </label><form:input path="id" type="number" /><br><br>
            <!--<input type="hidden" path="login" value="login" name="option" />-->
            <input type="submit"  value="Search"/>
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
