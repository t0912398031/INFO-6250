<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
	
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2",
	title: {
		text: "Bitcoin Market Price"
	},
	subtitles: [{
// 		text: "2000 to 2015"
	}],
	axisY:{
// 		title: "Percent of Population",
// 		suffix: "%",
		title: "Price",
		suffix: "$",
		includeZero: false
	},
	data: [{
		type: "spline",
		toolTipContent: "<b>{label}</b>: {y}$",
		dataPoints: <%= request.getAttribute("dataPoints")%>
	}]
});
chart.render();
 
}
</script>

<title>Market Price</title>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
<%-- 	<c:if test="${sessionScope.admin == 'admin'}"> --%>
	<form action="${contextPath}/signin/back" method ="post">
        <input type="hidden" name="option" value="logout"/>
        <input type="submit" value="Back"/>
    </form><br/>
<%--     </c:if>  --%>
    

	


        
        <div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	
</body>
</html>