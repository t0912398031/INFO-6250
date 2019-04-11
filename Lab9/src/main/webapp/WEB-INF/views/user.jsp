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
        	<form action="${contextPath}/admin/" method ="post">
		        <input type="hidden" name="option" value="logout"/>
		        <input type="submit" value="Back"/>
		    </form><br/>
		    
        	
            <h2> BitCoin Transcation Application</h2>
            <h2> Welcome ${sessionScope.USER.userName}</h2>
            <h2>Users:</h2>
        <table border="1">
            <thead>  
                <th>userName</th>
                <th>password</th>
                <th>name</th>
                <th>balance</th>
                <th></th>
            </thead>
            <tbody>           
            <c:forEach var="c" items="${clients}">
                <tr>
                    <td>${c.userName}</td>
                    <td>${c.password}</td>
                    <td>${c.name}</td>
                    <td>${c.balance}</td>
                    <td>
                    	<c:if test="${c.userName != sessionScope.USER.userName}">
	                    <form action="${contextPath}/admin/user/delete" method ="post">
				            <input type="hidden" name="delete" value=${c.userId} />
				            <input type="submit" value="Delete"/>
				        </form>
				        </c:if> 
			        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        
        
</body>
</html>