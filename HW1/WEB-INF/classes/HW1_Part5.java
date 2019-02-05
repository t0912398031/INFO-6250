
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HW1_Part5 extends HttpServlet{
    

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
        
        out.println("<H3>Following are the input coming from the Client<BR></H3>");
        out.println("<table border=2>");
        out.println("<tr><th>Key</th><th>Value</th></tr>");

        Map<String,String[]> allMap=request.getParameterMap();
        for(String key:allMap.keySet()){
            String[] strArr=(String[])allMap.get(key);
            for(String val:strArr){
                // System.out.println("Str Array= "+val);
                out.println("<tr><th>"+key + "</th><th>" + val + "</th></tr>");

            }   
        }

        out.println("</table>");			
        out.close();
        
    }
}