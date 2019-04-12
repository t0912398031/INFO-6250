<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Message View</title>
        </head>

        <body>
        	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <h2> BitCoin Transcation Application</h2>
            <h2> Welcome ${sessionScope.USER.userName}</h2>
            
        <form action="${contextPath}/signin/logout" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        
        <form action="${contextPath}/signin/order/create" method="POST">
            Price: <input type="number" name="price" />
            Amount: <input type="number" name="amount" />
            <select name="type">
            <option value="buy">Buy</option>
            <!--<option value="add">Add New Movie to Database</option>-->
            <option value="sell">Sell</option>
            </select>
            <input type="hidden" value="create" name="option" />
            <input type="submit" value="Create Order"/>
        </form>
        
        <form action="${contextPath}/signin/order" method="POST">     
            <input type="submit" value="View Orders"/>
        </form>
        
        <form action="${contextPath}/signin/marketprice" method="POST">     
            <input type="submit" value="Market Price"/>
        </form>
        
<%--         <form action="${contextPath}/signin/match" method="POST">      --%>
<!--             <input type="submit" value="Match Orders"/> -->
<!--         </form> -->
        
        
        
        
        
        <br><br><br><br><br><br>
        
        
        
        <h3>Courses Part</h3>
        <a href="addcourse.htm?formtype=addcourse"><h3>[Add Courses]</h3></a>
        <h4>Search</h4>
        <form action="search.htm" method="post">
            <label>Course Name</label><input type="radio" name="courseType" value="courseName" /><br/>
            <label>CRN Name</label><input type="radio" name="courseType" value="courseCRN" /><br/>
            <input type="text" name="searchBar" />
            <input type="submit" name="search" value="Search" />
        </form>
        
        <hr>
        
        <h3>Messages Part</h3>
        <h4>SEARCH USERS</h4>
        <form action="user.htm" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>
       
        <table>
      <c:if test="${not empty userMessages}">
            <thead>
            <th>User Name</th>
            <th>From User</th>
            <th>Message</th>
        </thead>
      </c:if>
        <tbody>
            <!--Implement Code here-->
            <c:forEach var="msg" items="${userMessages}"> 
                <tr>
                    <td><c:out value="${msg.getUserName()}" /></td>
                    <td><c:out value="${msg.getFromUser()}" /></td>
                    <td><c:out value="${msg.getMessage()}" /></td>
                    <td>
                        <form action="/Lab7_Hibernate/messageHome.htm" method="POST">
                            <input type='hidden' name='msgId' value='${msg.getId()}'/>
                            <input type='hidden' name='option' value='delete'/>
                            <input type="submit" value="Delete"/>
                        </form>
                    <td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</body>
</html>