/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hardik
 */
public class BookTableServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        int number = Integer.parseInt(req.getParameter("number"));
//        String userName = req.getParameter("userName");
//        String password = req.getParameter("password");
        String redirectPage = "index.html";


            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"addbook\" method=\"POST\">");

            out.println("<table>"
                    +"<thead>"
                    +"<th>ISBN</th>"
                    +"<th>Book Title</th>"
                    +"<th>Authors</th>"
                    +"<th>Price</th>"

                    +"</thead>"
                    +"<tbody>"
                    
            
            
            );
            
            for (int i = 0; i< number; i++) {

                out.println("<tr>"
                        +"<td><input type=\"text\" name=\"isbn\"></td>"
                        +"<td><input type=\"text\" name=\"title\"></td>"
                        +"<td><input type=\"text\" name=\"authors\"></td>"
                        +"<td><input type=\"text\" name=\"price\"></td>"           
                        );
//                Login login=new Login();
//                login.setUsername(rs.getString("UserName"));
//                users.add(login);                  
            } 
            out.println("<br>");
            out.println("<input type='submit' value='Add Books'/>"+"</form>");
            
//            out.println("<a href='movieStore.html'>Back to movie store.</a><br>");
            out.println("</body>");
            out.println("</html>"); 
        
            
        
//        resp.sendRedirect(redirectPage);
    }
}
