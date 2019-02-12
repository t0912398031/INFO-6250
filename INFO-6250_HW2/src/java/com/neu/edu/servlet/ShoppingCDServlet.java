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

public class ShoppingCDServlet extends HttpServlet {
    //Service method
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
   
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
//        String ans = request.getParameter("ans");
//        // Cookie ans1 = new Cookie("ans1", ans);
//        // response.addCookie(ans1);
//
//        HttpSession session = request.getSession();
//        session.setAttribute("ans", ans);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Books</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<a href='hw1part7'>Book</a><br/>");
        out.println("<a href='laptop'>Laptop</a><br/>");
        out.println("<a href='cd'>CD</a><br/>");
        
        out.println("<h2>Shop for Books</h2>");
        out.println("<form action = 'result' method = 'post'>");
        // out.println("<form action = 'quizservlet2?ans=" + ans + "' method = 'post'>");
        out.println("<input type='checkbox' name='cd' value='mandomna' />Mandomna<br />"
                + "<input type='checkbox' name='cd' value='spears' />Spears<br />"
                + "<input type='checkbox' name='cd' value='justin' />Justin<br />"
                + "<input type='checkbox' name='cd' value='nelly' />Nelly<br />"
                + "<input type='checkbox' name='cd' value='kanye' />Kanye<br />"
                + "<input type='hidden' name='type' value='cd' />"
                );
        out.println("<input type =  'submit' value = 'Next' name = 'button'/><br /></p>");
        out.println("</form>");
        out.println("<a href='cart'>View Cart</a><br/>");
        out.println("<br/><a href='index.html'>Home</a><br/>");
        out.println("</body>");
        out.println("</html>");
    }
}