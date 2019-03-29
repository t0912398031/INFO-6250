/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import java.util.Iterator;
import com.neu.dao.OrderDao;
import com.neu.pojo.Order;
import com.neu.pojo.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author balak
 */
public class HomeController extends AbstractController {

    public HomeController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = null;

        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("USER");

        if (loggedUser == null) {
            response.sendRedirect("/");
        } else {
            String option = request.getParameter("option") == null ? "" : request.getParameter("option");
//            MessageDao msgDao = (MessageDao) getApplicationContext().getBean("messageDAO");
            switch (option) {
                case "create":
//                    //actual send dao code
                    OrderDao orderDao = (OrderDao) getApplicationContext().getBean("orderdao");
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    int price = Integer.parseInt(request.getParameter("price"));
    
//                    User u = (User) getApplicationContext().getBean("user");

                    
                    
                    Order o = new Order();
                    o.setUser(loggedUser);
                    
                    Set orders = new HashSet<>();
                    orders.add(o);
                    
//                    Set orders = loggedUser.getOrder();  
////                    System.out.println(loggedUser.getName());
////                    System.out.println(orders.size());
//                    Iterator it=orders.iterator();
//                    while(it.hasNext())           {
//                        System.out.println(it.next());
//                        
//                    }
                    
                    
                    
                    
                    
//                    orders.add(o);

                    loggedUser.setOrder(orders);


                    o.setType((String) request.getParameter("type"));
                    o.setAmount(amount);
                    o.setPrice(price);


                    if(orderDao.add(o)==1){
                        System.out.println("order added successfully");
                        mv = new ModelAndView("loginPage");
                    } else{
                        System.out.println("order add fail");
                        mv = new ModelAndView("error", "message", "order add fail");
                    }
                    
                    break;
//                case "send":
//                    //actual send dao code
//
//                    String message = request.getParameter("message");
//                    String from = request.getParameter("sender");
//                    String to = request.getParameter("recipient");
//                    int result = msgDao.addMessages(to, from, message);
//
//                    if (result == 1) {
//
//                        mv = new ModelAndView(new RedirectView("/Lab7_Hibernate/messageHome.htm", false));
//
//                    } else {
//                        mv = new ModelAndView("error", "message", "Not able to send message");
//
//                    }
//
//                    break;
//                    
//                case "delete":
//                    long msgId = (long)Long.parseLong(request.getParameter("msgId"));
//                    msgDao.deleteMessageById(msgId);
//                    RedirectView view = new RedirectView("/Lab7_Hibernate/messageHome.htm");
//                    mv = new ModelAndView(view);
//                    break;

                default:
                    
//                    System.out.println("home page");
//                    List<Message> usersMessages = msgDao.getMessages(loggedUser.getUsername());
//                    mv = new ModelAndView("home", "userMessages", usersMessages);
                    mv = new ModelAndView("home", "loggedUser", loggedUser);
            }
        }

        return mv;
    }

}
