<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order List</title>

<style>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	<form action="${contextPath}/signin/order/back" method ="post">
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Back"/>
    </form><br/>
        	
		<table id="customers">
            <thead>
            	<th>orderId</th>
                <th>type</th>
                <th>amount</th>
                <th>price</th>
                <th>status</th>
                <th>date</th> 
                <th>dealdate</th> 
                <th>record</th> 
                <th>delete</th> 
            </thead>
            
            <tbody>           
            <c:forEach var="o" items="${orders}">
                <tr>
	                <th>${o.orderId}</th> 
                    <td>${o.type}</td>
                    <td>${o.amount}</td>
                    <td>${o.price}</td>
                    <td>${o.status}</td>
                    <td>${o.date}</td>
                    <td>${o.dealdate}</td>
                    <td>
					<form action="${contextPath}/signin/order/record" method ="post">
			            <input type="hidden" name="record" value=${o.orderId} />
			            <input type="submit" value="Record"/>
			        </form>
					</td>
                    <td>
                    <c:if test="${o.status == 'Pending'||o.status == 'Create'}">
	                	<form action="${contextPath}/signin/order/delete" method ="post">
				            <input type="hidden" name="delete" value=${o.orderId} />
				            <input type="submit" value="Cancel"/>
				        </form>
	                </c:if> 
                    
			        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table><br/>

    	
        <form action="${contextPath}/signin/order/match" method ="post">
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Confirm"/>
    	</form><br/>
	
</body>
</html>