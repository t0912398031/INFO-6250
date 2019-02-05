
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HW1_Part6 extends HttpServlet{
    

    @Override
    public void init(){

    }

            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // processRequest(request, response);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<H3>How many children do you have?<BR></H3>");
        out.println("<form action=\"part6\" method=\"post\">");
        out.println("Please enter the name of child 1 <input type=\"text\" name=\"child\"><br /><br/>");
        out.println("Please enter the name of child 2 <input type=\"text\" name=\"child\"><br /><br/>");
        out.println("Please enter the name of child 3 <input type=\"text\" name=\"child\"><br /><br/>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");


        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<H3>Your Children's names are:<BR></H3>");
        // out.println("<form action=\"myform\" method=\"post\">");
        // out.println("Please enter the name of child 1 <input type=\"text\" name=\"child\"><br /><br/>");
        // out.println("Please enter the name of child 2 <input type=\"text\" name=\"child\"><br /><br/>");
        // out.println("Please enter the name of child 3 <input type=\"text\" name=\"child\"><br /><br/>");
        // out.println("<input type=\"submit\" value=\"Submit\">");
        // out.println("</form>");
        String[] children = request.getParameterValues("child");
        out.println("<ul>");
        for(String c: children){
            out.println("<li>"+c+"</li>");
        }

        // Enumeration<String> parameterNames = request.getParameterNames();
        // while (parameterNames.hasMoreElements()) {
        //     String key = (String) parameterNames.nextElement();
        //     String val = request.getParameter(key);
        //     System.out.println("A= <"+key+"> Value<"+val+">");
        //     out.println("<tr><th>"+key + "</th><th>" + val + "</th></tr>");
        // }

        out.close();
        
    }

    
}