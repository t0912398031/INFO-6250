<%-- 
    Document   : messages
    Created on : Feb 1, 2019, 1:21:27 AM
    Author     : Hardik
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>  


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<jsp:useBean id="USER" type="com.neu.edu.pojo.Login" scope="session"></jsp:useBean>--%>
<%--<jsp:useBean id="usersMessages" class="Message" scope="request"></jsp:useBean>--%>
<jsp:useBean id="random" class="com.neu.edu.pojo.RandomBean" scope="application" />


    <!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message View</title>
    </head>
    
    <body>
        <h4>c:import fn:length, substring, replace</h4>
        <c:import var="data" url="https://www.google.com/"/>  
        <div>google site:</div>
        <c:out value="${fn:substring(data, 0, 100)}"/>
        <br>
        <div>length: <c:out value="${fn:length(data)}"/>  </div>
        <%--<c:out value="${data.length()}"/>--%>

        <c:set var="string" value="${fn:substring(data, 0, 100)}" />
        <c:set var="string2" value="${fn:replace(string, '<', '_')}" />
        <c:set var="string3" value="${fn:replace(string2, '>', '_')}" />
        <div>after replace: ${string3}</div>
        
        <h4>c:set, fmt:parseNumber</h4>
        <c:set var="num" scope="session" value="${1000/3}"/>  
        <fmt:parseNumber var="p" integerOnly="true" type="number" value="${num}" />  
        <div>1000/3 integer part= <c:out value="${p}"/></div>
        
        <h4>c:if, redirect</h4>
        <c:set var="url" value="0" scope="request"/>  
        <c:if test="${random.nextInt>7}">  
           <%--<c:redirect url="https://www.google.com"/>--%>  
        </c:if>  
        <c:if test="${random.nextInt<=7}">  
            <c:out value="${'random number <= 7, if > 7 redirect to Google'}"/>        
        </c:if>  
        
        <h4>fmt:formatDate, setTimeZone</h4>
        <c:set var="date" value="<%=new java.util.Date()%>" /> 
        <div>Current Time: <fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /></div> 
        <fmt:setTimeZone value="GMT-10" />
        <div>GMT-10 time Zone: <fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /></div>
        
        
        
        <h4>x:out, set</h4> 
        <c:set var="vegetable">  
        <vehicles>  
            <vehicle>  
                <name>Car</name>  
                <price>100000$</price>  
            </vehicle>  
            <vehicle>  
                <name>Airplane</name>  
                <price>5000000$</price>  
            </vehicle>  
            <vehicle>  
                <name>Bicycle</name>  
                <price>100$</price>  
            </vehicle>  
        </vehicles>  
        </c:set>  
        <x:parse xml="${vegetable}" var="output"/>  
        <b>Name of the vehicle is</b>:  
        <x:out select="$output/vehicles/vehicle[1]/name" /><br>  
        <b>Price of Airplane is</b>:  
        <x:set var="airplane" select="$output/vehicles/vehicle[2]/price"/>  
        <x:out select="$airplane"  />  
        
        
    </body>
</html>