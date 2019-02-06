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

public class CartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
//        String[] book = request.getParameterValues("book");
        String type = request.getParameter("type");
//        String ans = (String)session.getAttribute("ans");
//        String ans2 = (String)session.getAttribute("ans2");
        String[] books = (String[])session.getAttribute("book");
        String[] laptops = (String[])session.getAttribute("laptop");
        String[] cds = (String[])session.getAttribute("cd");



        out.println("<html>");
        out.println("<head>");
        out.println("<title>Books</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Cart Items:</h2>");
        for(String b: books){
            out.println("<div>"+b+"</div>");
        }
        for(String l: laptops){
            out.println("<div>"+l+"</div>");
        }
        for(String c: cds){
            out.println("<div>"+c+"</div>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}