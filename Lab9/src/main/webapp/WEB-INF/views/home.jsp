<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
</head>
<body>
	<h1>Welcome to Lab 9!</h1>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/create">Create users and categories :</a>
	<a href="${contextPath}/advert/create">Create advert :</a>
	
	<a href="${contextPath}/createClient">Create client</a>
	<a href="${contextPath}/register/">register :</a>

	
	<form action="${contextPath}/signin/" method="POST">
        <label>username :</label>  <input type="text" name="userName" required><br /><br/>
        <label>password :</label>  <input type="text" name="password" required><br /><br/>
        
        
        <input type="hidden" value="addUser" name="userOption" />
        <input type="submit" value="Sign In"/>
    </form>
    <br>
    ${sessionScope.error}

</body>
</html>
