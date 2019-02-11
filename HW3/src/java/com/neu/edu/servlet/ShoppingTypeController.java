/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Chung-Yang Li
 */
public class ShoppingTypeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String option = req.getParameter("option");
        RequestDispatcher requestDispatcher = null;
//        System.out.println(option);
        if (option.equals("book")) {
            req.setAttribute("option", option);
            List<String> item = Arrays.asList("java", "oracle", "uml", "object", "web");
            req.setAttribute("items", item);
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp");
            requestDispatcher.forward(req, resp); 

        } else if (option.equals("laptop")) {
            req.setAttribute("option", option);
            List<String> item = Arrays.asList("apple", "asus", "hp", "toshiba", "sony");
            req.setAttribute("items", item);
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp");
            requestDispatcher.forward(req, resp);

        } else if (option.equals("cd")){
            req.setAttribute("option", option);
            List<String> item = Arrays.asList("mandomna", "spears", "justin", "nelly", "kanye");
            req.setAttribute("items", item);
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp");
            requestDispatcher.forward(req, resp);
            
        } else if (option.equals("cart")){
            
            req.setAttribute("option", option);
            String type = req.getParameter("type");
            
            if(type.equalsIgnoreCase("book")){
                String[] book = req.getParameterValues("book");
                session.setAttribute("book", book);
                req.setAttribute("items", book);
//                for(String l: book){
//                    System.out.println(l);
//                }
            }else if(type.equalsIgnoreCase("laptop")){
                String[] laptop = req.getParameterValues("laptop");
                session.setAttribute("laptop", laptop);
                req.setAttribute("items", laptop);
//                for(String l: laptop){
//                    System.out.println(l);
//                }
            }else if(type.equalsIgnoreCase("cd")){
                String[] cd = req.getParameterValues("cd");
                session.setAttribute("cd", cd);
                req.setAttribute("items", cd);
//                for(String c: cd){
//                    System.out.println(c);
//                }
            }

            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher requestDispatcher = null;
        
        req.setAttribute("option", "book");
        List<String> item = Arrays.asList("java", "oracle", "uml", "object", "web");
        req.setAttribute("items", item);
        requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp");
        requestDispatcher.forward(req, resp);
//        Login loggeduser = (Login) session.getAttribute("USER");
//        if (loggeduser == null) {
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
//            requestDispatcher.forward(req, resp);
//        } else {
//            String sendTo = req.getParameter("toUser");
//            req.setAttribute("toUser", sendTo);
//            RequestDispatcher requestDispatcher = null;
//            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/sendMessage.jsp");
//            requestDispatcher.forward(req, resp);
//        }
    }

}
