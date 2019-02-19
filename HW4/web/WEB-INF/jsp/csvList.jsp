<%-- 
 /**
 *
 * @author Chung-Yang Li
 */
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.neu.pojo.Row"%>
<%@page import="com.neu.tag.MyTag"%>

<%--<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>--%>
<%--<jsp:useBean id="USER" type="com.neu.edu.pojo.Login" scope="session"></jsp:useBean>--%>
<%--<jsp:useBean id="usersMessages" class="Message" scope="request"></jsp:useBean>--%>
    <!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSV</title>
    </head>
    
    <body>
        <!--<a href='index.jsp'>Home</a><br/>-->
        <form action="part.htm" method="POST">          
            <input type="hidden" value="" name="option" />
            <input type="submit" value="Home"/>
        </form>
        
        <h1>List:</h1>
<!--        <form action = 'part.htm' method = 'post'>
        <input type="text" name="foldername">
        <input type="hidden" value="csv" name="option" />
        <input type = 'submit' value = 'Submit' name = 'button'/>
        </form>-->
        
        <%--<c:if test="${requestScope.resultSet != null}">--%>
            
        <%if (request.isUserInRole("admin")) {%>
          
        <%@ taglib uri="/WEB-INF/tlds/mytags.tld" prefix="m" %>  
        <%String a= request.getParameter("foldername");%>
        <m:csv message="<%=a%>"/> 
        <%--<m:csv message = "This is custom tag" />--%>
        
        <% } %>
        
        <%--</c:if>--%> 
        
        
        <c:if test="${requestScope.resultSet != null}"> 
            <c:set var="rs" value="${requestScope.resultSet}" scope="request"/> 
            <c:set var="size" value="${requestScope.size}" scope="request"/>
        <%--<c:out value="${rs.next()}"/>--%>
        <%--<c:out value="${size}"/>--%>
        
        <table>
        <c:forEach var="r" items="${requestScope.rows}">
            <%--<c:out value="${r}"/>--%>
            <%--<c:out value="${r.getString1()}"/>--%>
            <%--<c:out value="${r.getString2()}"/>--%>
            <tr>
                <td>${r.getString1()}</td>
                <td>${r.getString2()}</td>
            </tr>
        </c:forEach>
        </table>
    
        </c:if>  
        
        
<!--        
        <form action="shoppingtype" method ="post">
            <input type="hidden" name="option" value="book"/>
            <input type="submit" value="Book"/>
        </form><br/>
        <form action="shoppingtype" method ="post">
            <input type="hidden" name="option" value="laptop"/>
            <input type="submit" value="Laptop"/>
        </form><br/>
        <form action="shoppingtype" method ="post">
            <input type="hidden" name="option" value="cd"/>
            <input type="submit" value="CD"/>
        </form><br/>
        
        -->
        
<!--        <form action="login" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        <h4>SEARCH USERS</h4>
        <form action="user" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>-->
    



</body>
</html>