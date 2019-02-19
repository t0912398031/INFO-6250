<jsp:useBean id="books" type="java.util.ArrayList" scope="request"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP page</title>
    </head>

    <body>
        <h1>Book list</h1>
        <%
            for(int i=0; i< books.size(); i++){
                out.println(books.get(i) + "<br>");
            }
        %>
    </body>
</html>
