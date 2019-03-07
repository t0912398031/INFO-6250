<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <c:if test="${requestScope.q ne 'answers'}">
            
            <form action = "${requestScope.q + 1}.htm" method = 'post'>
            <input type='radio' name='ans' value='1' />${requestScope.question.getQ1()}<br />
            <input type='radio' name='ans' value='2' />${requestScope.question.getQ2()}<br />
            <input type='radio' name='ans' value='3' />${requestScope.question.getQ3()}<br />
            <input type='radio' name='ans' value='4' />${requestScope.question.getQ4()}<br />
            
            <input type =  'submit' value = 'Next' name = 'button'/>
            </form>
          
            <!--<a href="${requestScope.q + 1}.htm">Next</a>-->
        </c:if>
            
        <c:if test="${requestScope.q eq 'answers'}">
            Question1: ${sessionScope.answer1}<br>
            Question2: ${sessionScope.answer2}<br>
            Question3: ${sessionScope.answer3}<br>
            Question4: ${sessionScope.answer4}<br>
            Question5: ${sessionScope.answer5}<br>
            Question6: ${sessionScope.answer6}<br>
            Question7: ${sessionScope.answer7}<br>
            Question8: ${sessionScope.answer8}<br>
            Question9: ${sessionScope.answer9}<br>
            Question10: ${sessionScope.answer10}
        </c:if>
    </body>
</html>
