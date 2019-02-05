
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Enumeration;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class HW1_Part4 extends HttpServlet{
    
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    @Override
    public void init(){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload"); 
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
        String fileName = "";


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<H3>Following are the input coming from the Client<BR></H3>");
        out.println("<table border=2>");
        out.println("<tr><th>Key</th><th>Value</th></tr>");


        // Enumeration<String> parameterNames = request.getParameterNames();
        // while (parameterNames.hasMoreElements()) {
        //     String key = (String) parameterNames.nextElement();
        //     String val = request.getParameter(key);
        //     System.out.println("A= <"+key+"> Value<"+val+">");
        //     out.println("<tr><th>"+key + "</th><th>" + val + "</th></tr>");
        // }

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);

        if( !isMultipart ) {          
            out.println("<p>No file uploaded</p>");            
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
   
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
    
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
    
        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try { 
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
       
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
   
            
            out.println("<title>Servlet upload</title>");  
            
      
            while ( i.hasNext () ) {
               FileItem fi = (FileItem)i.next();

               if (fi.isFormField()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                String fieldname = fi.getFieldName();
                String fieldvalue = fi.getString();
                
                System.out.println("A= <"+fieldname+"> Value<"+fieldvalue+">");

                out.println("<tr><th>"+fieldname + "</th><th>" + fieldvalue + "</th></tr>");
                }

               if ( !fi.isFormField () ) {
                  // Get the uploaded file parameters
                  String fieldName = fi.getFieldName();
                  fileName = fi.getName();
                  String contentType = fi.getContentType();
                  boolean isInMemory = fi.isInMemory();
                  long sizeInBytes = fi.getSize();
               
                  // Write the file
                  if( fileName.lastIndexOf("\\") >= 0 ) {
                     file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                  } else {
                     file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                  }
                  fi.write( file ) ;
                //   out.println("Uploaded Filename: " + fileName + "<br>");
               }
            }
            
        } 
        catch(Exception ex) {
            System.out.println(ex);
        }


        

        out.println("</table>");	
        
        out.println("Uploaded Filename: " + fileName + "<br>");
        out.close();
        
    }
}