<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Signin View</title>
            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        </head>

        <body>
        	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <h2> BitCoin Transcation Application</h2>
            <h2> Welcome ${sessionScope.USER.userName}</h2>
            <h4> Your user ID: ${sessionScope.USER.userId}</h4>
            
        <form action="${contextPath}/signin/logout" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        
        Your current balance: $ ${sessionScope.USER.balance}
        <br>
        Your current bitcoins: ${sessionScope.USER.bitcoins.size()}
        
        <form action="${contextPath}/signin/bitcoins" method="POST">     
            <input type="submit" value="Bitcoins"/>
        </form><br>
  
  <div ng-app="myApp" ng-controller="myCtrl">    
    
        <form action="${contextPath}/signin/order/create" method="POST">
            Price: <input type="number" name="price" ng-model="p" required />
            Amount: <input type="number" name="amount" required/>
            <select name="type">
            <option value="buy">Buy</option>

            <option value="sell">Sell</option>
            </select>
<!--             <input type="hidden" value="create" name="option" /> -->
            <input type="submit" value="Create Order"/>
        </form>
        <div ng-if="p>2000">Warning, the price too high</div>
        <div ng-if="p<1000">Warning, the price too low</div>
	</div>
        ${requestScope.error}
        
        
        <br><br>
        
        <form action="${contextPath}/signin/order/" method="POST">     
            <input type="submit" value="View Orders"/>
        </form><br>
        
        <form action="${contextPath}/signin/marketprice" method="POST">     
            <input type="submit" value="Market Price"/>
        </form><br>
        

<script>
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope) {
		$scope.p = 1500;
	});
</script>
</body>
</html>