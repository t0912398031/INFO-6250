/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.UserDao;
import com.neu.pojo.BitCoin;
import com.neu.pojo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Chung-Yang Li
 */
public class UserController extends AbstractController {

    public UserController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        UserDao userDao;
        ModelAndView mv = null;
        
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
//        User loggeduser = (User) session.getAttribute("USER");
//        ModelAndView mv = null;
//        if (loggeduser == null) {
//            mv = new ModelAndView("loginPage");
//        } else {

            String option = request.getParameter("userOption") == null ? "" : request.getParameter("userOption");

            switch (option) {
                case "addUser":
                    userDao = (UserDao) getApplicationContext().getBean("userdao");       
                    User u = (User) getApplicationContext().getBean("user");
                    
                    
                    BitCoin b = new BitCoin("1BvBMSEYstW");
                    b.setOwner((String) request.getAttribute("name"));


                    u.setUserName((String) request.getAttribute("username"));
                    u.setPassword((String) request.getAttribute("password"));
                    u.setName((String) request.getAttribute("name"));
                    u.setBalance(Integer.parseInt(request.getParameter("balance")));


                    if(userDao.add(u)==1){
                        System.out.println("user added successfully");
                        mv = new ModelAndView("loginPage");
                    } else{
                        System.out.println("user add fail");
                        mv = new ModelAndView("error", "message", "user add fail");
                    }
                    break;
                case "sendMessageForm":
                    String recipient = request.getParameter("recipient");
                    mv = new ModelAndView("sendMessageForm", "recipient", recipient);
                    break;

                default:
//                    String searchString = request.getParameter("search");
//                    if (searchString == null || searchString.isEmpty()) {
//                        mv = new ModelAndView(new RedirectView("/Lab7_Hibernate/messageHome.htm",false));
//                    } else {
//                        UserDao user = (UserDao) getApplicationContext().getBean("userDAO");
//                        List<Login> users = user.getUsers(searchString);
//                        if (users.size() == 0) {
//                            mv = new ModelAndView("error", "message", "No matching users");
//                        } else {
//                            mv = new ModelAndView("listUsers", "matchedUsers", users);
//                        }
//                    }
            }

//        }

        return mv;

    }
}
