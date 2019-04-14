<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Message View</title>
        </head>

        <body>
        	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <h2> BitCoin Transcation Application</h2>
            <h2> Welcome ${sessionScope.USER.userName}</h2>
            
        <form action="${contextPath}/signin/logout" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>

        <form action="${contextPath}/admin/user" method="POST">     
            <input type="submit" value="View Users"/>
        </form><br>
        
        <form action="${contextPath}/admin/view" method="POST">     
            <input type="submit" value="View Orders"/>
        </form><br>
        
        <form action="${contextPath}/admin/match" method="POST">     
            <input type="submit" value="Match Orders"/>
        </form>

        
</body>
</html>