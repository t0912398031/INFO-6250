/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.AdvertDao;
import com.neu.dao.CategoryDao;
import com.neu.dao.UserDao;
import com.neu.pojo.Advert;
import com.neu.pojo.BitCoin;
import com.neu.pojo.Category;
import com.neu.pojo.Phone;
import com.neu.pojo.User;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Adi
 */
public class UserController extends AbstractController {

    UserDao userDao;
    AdvertDao advertDao;
    CategoryDao categoryDao;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
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
                    
                    String random1 = RandomStringUtils.random(15, true, true);
                    String random2 = RandomStringUtils.random(15, true, true);
//                    System.out.println(random1);
                    
                    BitCoin b1 = new BitCoin(random1);
                    b1.setUser(u);
                    BitCoin b2 = new BitCoin(random2);
                    b2.setUser(u);
//                    Set<BitCoin> bitcoins = new HashSet<BitCoin>();
                    Set bitcoins = new HashSet<>();
                    bitcoins.add(b1);
                    bitcoins.add(b2);
                    u.setBitCoins(bitcoins);
                    
//                    b.setOwner((String) request.getAttribute("name"));


                    u.setUserName((String) request.getParameter("username"));
                    u.setPassword((String) request.getParameter("password"));
                    u.setName((String) request.getParameter("name"));
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
//                        BitCoinDao user = (BitCoinDao) getApplicationContext().getBean("userDAO");
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

    
        
        
        
        
        
        
        
        
//        // Userdao, phone, user
//        userDao = (UserDao) getApplicationContext().getBean("userdao");
////        advertDao = (AdvertDao) getApplicationContext().getBean("advertdao");
//        User u = (User) getApplicationContext().getBean("user");
//
//        BitCoin ph1 = new BitCoin("1231231");
//        ph1.setUser(u);
//        BitCoin ph2 = new BitCoin("98989");
//        ph2.setUser(u);
//
//        Set set1 = new HashSet<>();
//        set1.add(ph1);
//        set1.add(ph2);
//
//        u.setName("Lipang");
//        
//
//        userDao.add(u);
////        Advert ad = new Advert("Advert title1", "Advert message ", u);
////        advertDao.create(ad);
////
////        Set set2 = new HashSet<>();
////        set2.add(ad);
////        Category cat = new Category("Category Title ", set2);
////        categoryDao = (CategoryDao) getApplicationContext().getBean("categorydao");
////        categoryDao.create(cat);
//        return new ModelAndView("user");
    }

}
