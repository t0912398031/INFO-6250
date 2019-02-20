/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.pojo.Item;
import java.io.IOException;
import java.util.ArrayList;
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

        if (option.equals("book")||option.equals("laptop")||option.equals("cd")) {
            req.setAttribute("option", option);
            Item i = new Item();
            i.setType(option);
            req.setAttribute("items", i.getItemByType());
            
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp");
            requestDispatcher.forward(req, resp); 
   
        } else if (option.equals("cart")){
            
            req.setAttribute("option", option);
            String type = req.getParameter("type");
    
            String[] item = req.getParameterValues(type);
            String[] items = null;
            if(session.getAttribute("book")!=null){
                items = (String[])session.getAttribute("book");
                List<String> list = new ArrayList<String>(Arrays.asList(items));     
                for(String i:item){
                    list.add(i);
                }
                items = list.toArray(new String[0]);
                session.setAttribute("book", items);
                req.setAttribute("items", item);
            } else{
                session.setAttribute("book", item);
                req.setAttribute("items", item);
            }


            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
            requestDispatcher.forward(req, resp);
        } else if (option.equals("delete")) {
            String delete = req.getParameter("delete");
            String type = req.getParameter("type");
            
            
            if(type.equalsIgnoreCase("cd")){
                String[] cd = (String[])session.getAttribute("cd");
//                for(String b: cd){System.out.println(b);}

                List<String> list = new ArrayList<String>(Arrays.asList(cd));
                list.remove(delete);
                cd = list.toArray(new String[0]);
//                for(String b: cd){System.out.println(b);}
                session.setAttribute("cd", cd);
            } else if(type.equalsIgnoreCase("book")){
                String[] cd = (String[])session.getAttribute("book");

                List<String> list = new ArrayList<String>(Arrays.asList(cd));
                list.remove(delete);
                cd = list.toArray(new String[0]);

                session.setAttribute("book", cd);
            } else if(type.equalsIgnoreCase("laptop")){
                String[] cd = (String[])session.getAttribute("laptop");


                List<String> list = new ArrayList<String>(Arrays.asList(cd));
                list.remove(delete);
                cd = list.toArray(new String[0]);

                session.setAttribute("laptop", cd);
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
