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
        
        Your current balance: $ ${sessionScope.USER.balance}
        <br>
        Your current bitcoins: ${sessionScope.USER.bitcoins.size()}
        
        <form action="${contextPath}/signin/order/create" method="POST">
            Price: <input type="number" name="price" />
            Amount: <input type="number" name="amount" />
            <select name="type">
            <option value="buy">Buy</option>
            <!--<option value="add">Add New Movie to Database</option>-->
            <option value="sell">Sell</option>
            </select>
            <input type="hidden" value="create" name="option" />
            <input type="submit" value="Create Order"/>
        </form><br>
        
        <form action="${contextPath}/signin/order" method="POST">     
            <input type="submit" value="View Orders"/>
        </form><br>
        
        <form action="${contextPath}/signin/marketprice" method="POST">     
            <input type="submit" value="Market Price"/>
        </form><br>
        

   
</body>
</html>