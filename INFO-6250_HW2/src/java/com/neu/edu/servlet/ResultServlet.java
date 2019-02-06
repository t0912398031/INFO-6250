package com.neu.edu.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResultServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
//        String[] book = request.getParameterValues("book");
        String type = request.getParameter("type");
//        String ans = (String)session.getAttribute("ans");
//        String ans2 = (String)session.getAttribute("ans2");

    




        out.println("<html>");
        out.println("<head>");
        out.println("<title>Books</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>The following item has been added to your cart successfully</h2>");

        if(type.equalsIgnoreCase("book")){
            String[] book = request.getParameterValues("book");
            session.setAttribute("book", book);

            for(String b: book){
                out.println("<div>" + b + "</div>");
            }

        }else if(type.equalsIgnoreCase("laptop")){
            String[] laptop = request.getParameterValues("laptop");
            session.setAttribute("laptop", laptop);
            
            for(String l: laptop){
                out.println("<div>" + l + "</div>");
            }
        }else if(type.equalsIgnoreCase("cd")){
            String[] cd = request.getParameterValues("cd");
            session.setAttribute("cd", cd);
            
            for(String c: cd){
                out.println("<div>" + c + "</div>");
            }
        }
        // out.println("<form action = 'quizservlet2?ans=" + ans + "' method = 'post'>");

        out.println("<a href='cart'>View Cart</a><br/>");
        out.println("</body>");
        out.println("</html>");
    }
}