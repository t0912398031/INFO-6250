<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Welcome to Lab 9!</h1>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/create">Create users and categories :</a>
	<a href="${contextPath}/advert/create">Create advert :</a>
</body>
</html>
