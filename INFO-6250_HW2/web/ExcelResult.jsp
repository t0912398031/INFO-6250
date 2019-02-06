<%-- 
    Document   : listUsers
    Created on : Feb 1, 2019, 7:52:28 PM
    Author     : Hardik
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.neu.edu.pojo.Login"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class = "table">
        <% ArrayList<List<String>> display = (ArrayList<List<String>>) request.getAttribute("table");
                        for(int m = 0; m < display.size(); m ++)
                        {
                            ArrayList<String> sublist = (ArrayList<String>) display.get(m);
                            if(m == 0)
                            {
                                out.println("<tr>");
                                for (String excel : sublist)
                                {
                                    out.println("<th>" + excel + "</h>");
                                }
                                out.println("</tr>");
                            }

                            else
                            {
                                out.println("<tr>");
                                for (String excel : sublist)
                                {
                                    out.println("<td>" + excel + "</td>");
                                }
                                out.println("</tr>");
                            }
                        } %>      
        </table>  
    </body>
</html>
