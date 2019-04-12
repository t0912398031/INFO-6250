<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advert form</title>

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
	
	<c:if test="${sessionScope.admin == 'admin'}">
	<form action="${contextPath}/admin/view" method ="post">
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Back"/>
    </form><br/>
    </c:if> 
    
    <c:if test="${sessionScope.admin == null}">
	<form action="${contextPath}/signin/order" method ="post">
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Back"/>
    </form><br/>
	</c:if> 	
	
    
<%--     <form action="${contextPath}/admin/view" method ="post"> --%>
<!-- 	    <select name="search"> -->
<!--     	  <option value="userId">UserID</option> -->
<!-- 		  <option value="date">Date</option> -->
<!-- 		  <option value="type">UserID</option> -->
<!-- 		  <option value="userId">UserID</option> -->
<!-- 		  <option value="date">Date</option> -->
<!-- 		  <option value="userId">UserID</option>		  -->
<!-- 		</select> -->
<!--         <input type="hidden" name="option" value="logout"/> -->
<!--         <input type="submit" value="Sort"/> -->
<%--     </form><br/> --%>
    
<%--         <c:set var="type" value="${records.get(0).type}" /> --%>
		<table id="customers">
            <thead>
            	
                <th>orderId</th>
                <th>type</th>
            </thead>
            <tbody>           
            
               <tr>
<%--                	   <td>${records.get(0).orderId}</td> --%>
<%--                    <td>${records.get(0).type}</td>  --%>
                   <td>${order.orderId}</td>
                   <td>${order.type}</td>             
               </tr>
            
            </tbody>
        </table>    
    	<br>
		<table id="customers">
            <thead>
            	<c:if test="${order.type == 'buy'}">
                	<th>target sellerId</th>
                </c:if> 
                <c:if test="${order.type == 'sell'}">
                	<th>target buyerId</th>
                </c:if> 
<!--                 <th>target user</th> -->
                <th>amount</th>
                <th>price</th>               
                <th>date</th> 
                
            </thead>
            <tbody>           
            <c:forEach var="o" items="${records}">
                <tr>	                
                    <td>${o.targetId}</td>
                    <td>${o.amount}</td>
                    <td>${o.price}</td>
                    <td>${o.date}</td>
                    
                    
                </tr>
            </c:forEach>
            </tbody>
        </table>
        
        
	
</body>
</html>