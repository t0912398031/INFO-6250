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
	<form:form action="${contextPath}/advert/create" method="post"
		modelAttribute="advert">
		<table>
			<tr>
				<td>User Id :</td>
				<td><input name="userId" required /></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>
			<tr>
				<td>Advert Title:</td>
				<td><form:input type="text" path="title" required="required" /></td>
			</tr>
			<tr>
				<td>Message:</td>
				<td><form:input type="text" path="message" required="required" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Post Advert" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>