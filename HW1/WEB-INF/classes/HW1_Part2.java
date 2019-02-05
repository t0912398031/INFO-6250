
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HW1_Part2 extends HttpServlet{
    

    @Override
    public void init(){

    }

            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Enumeration e = request.getHeaderNames();       // gets all headers information
 
        out.println("<H3>Client Headers Information<BR></H3>");
     
        out.println("<table border=2 bordercolor=blue>");
     
        out.println("<tr><th>Header Name</th><th>Header Value</th></tr>");
     
        while(e.hasMoreElements())
        {
          String name = (String) e.nextElement();
          String value = request.getHeader(name);       // gets each header information separately
          out.println("<tr><th>"+name + "</th><th>" + value + "</th></tr>");
        }
        out.println("</table>");			
        out.close();
        
    }
}