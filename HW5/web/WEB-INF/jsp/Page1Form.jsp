<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        Question ${requestScope.q} : ${requestScope.question.getQuestion()}

        
        <br/>
        
            
       

	<form:form method="POST" commandName="quizForm">
	<%--<form:errors path="*" cssClass="errorblock" element="div" />--%>
        <form:radiobutton value='1' path="a${requestScope.q}" />${requestScope.question.getQ1()}<br />
        <form:radiobutton value='2' path="a${requestScope.q}" />${requestScope.question.getQ2()}<br />
        <form:radiobutton value='3' path="a${requestScope.q}" />${requestScope.question.getQ3()}<br />
        <form:radiobutton value='4' path="a${requestScope.q}" />${requestScope.question.getQ4()}<br />
        <form:errors path="*" />
          <br>
			<input type="hidden" name="page" value=${requestScope.q} />
                        
                        <c:if test="${requestScope.q ne '1'}">
                            <input type="submit" value="Previous"name="_target0" />
                        </c:if>
                            
                        <c:if test="${requestScope.q ne '10'}">    
                            <input type="submit" value="Next" name="_target0" />
                        </c:if>
                        <c:if test="${requestScope.q eq '10'}">
                            <input type="submit" value="Finish" name="_finish" />
                        </c:if>
                        <input type="submit" value="Cancel" name="_cancel" />
			
	</form:form>
    </body>
</html>
