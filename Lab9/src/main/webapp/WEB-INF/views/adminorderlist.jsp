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
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		
	<form action="${contextPath}/admin/back" method ="post">
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Back"/>
    </form><br/>
    
    <form action="${contextPath}/admin/view" method ="post">
	    <select name="search">
    	  <option value="userId">UserID</option>
		  <option value="date">Date</option>
		  <option value="type">UserID</option>
		  <option value="userId">UserID</option>
		  <option value="date">Date</option>
		  <option value="userId">UserID</option>		 
		</select>
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Sort"/>
    </form><br/>
    
		<table border="1">
            <thead>
            	<c:if test="${requestScope.admin == 'admin'}">
                	<th>userId</th>
                </c:if> 
                <th>type</th>
                <th>amount</th>
                <th>price</th>
                <th>status</th>
                <th>date</th> 
                <th>view record</th> 
                <th></th> 
            </thead>
            <tbody>           
            <c:forEach var="o" items="${buyorders}">
                <tr>
	                <c:if test="${requestScope.admin == 'admin'}">
	                	<th>${o.userId}</th>
	                </c:if> 
                    <td>${o.type}</td>
                    <td>${o.amount}</td>
                    <td>${o.price}</td>
                    <td>${o.status}</td>
                    <td>${o.date}</td>
                    <td>
					<form action="${contextPath}/signin/order/record" method ="post">
			            <input type="hidden" name="record" value=${o.orderId} />
			            <input type="submit" value="Record"/>
			        </form>
					</td>
                    <td>
                    <form action="${contextPath}/signin/order/delete" method ="post">
			            <input type="hidden" name="delete" value=${o} />
			            <input type="submit" value="Delete"/>
			        </form>
			        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        
        <table border="1">
            <thead>
            	<c:if test="${requestScope.admin == 'admin'}">
                	<th>userId</th>
                </c:if> 
                <th>type</th>
                <th>amount</th>
                <th>price</th>
                <th>status</th>
                <th>date</th> 
                <th>view record</th> 
                <th></th> 
            </thead>
            <tbody>           
            <c:forEach var="o" items="${sellorders}">
                <tr>
	                <c:if test="${requestScope.admin == 'admin'}">
	                	<th>${o.userId}</th>
	                </c:if> 
                    <td>${o.type}</td>
                    <td>${o.amount}</td>
                    <td>${o.price}</td>
                    <td>${o.status}</td>
                    <td>${o.date}</td>
                    <td>
                    <form action="${contextPath}/signin/order/record" method ="post">
			            <input type="hidden" name="record" value=${o.orderId} />
			            <input type="submit" value="Record"/>
			        </form></td>
                    <td>
                    <form action="${contextPath}/signin/order/delete" method ="post">
			            <input type="hidden" name="delete" value=${o} />
			            <input type="submit" value="Delete"/>
			        </form>
			        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
	
</body>
</html>