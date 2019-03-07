/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.dao.UserDao;
import com.neu.pojo.Login;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author harsh
 */
public class PartController extends AbstractController {

    public PartController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

//        HttpSession session = request.getSession();
        ModelAndView mv = null;
        
        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
//        System.out.println(option);
        switch (option) {
            case "csv":
//                session.invalidate();
                
                mv = new ModelAndView("csv");
//                mv = new ModelAndView(new RedirectView("/HW4/csv.htm", false));
                break;
//            case "csvlist":
////                session.invalidate();
//                String filename = request.getParameter("foldername");
//                if(filename.equals("SalesOrder")){
//                    
//                    mv = new ModelAndView("csvList");
//                }
//                else{
//                    
//                    mv = new ModelAndView("csv");
//                }
////                mv = new ModelAndView(new RedirectView("/HW4/csv.htm", false));
//                break;
            case "movie":
//                session.invalidate();
            mv = new ModelAndView("movie");
//                mv = new ModelAndView(new RedirectView("/HW4/csv.htm", false));
            break;
//            case "login":
//                Login loggedUser = user.authenticateLogin(userName, password);
//                if (loggedUser == null) {
//                    mv = new ModelAndView("loginPage");
//                } else {
//                    //Redirect to messages controller
//                    //response.sendRedirect("/SpringMVC/messageHome.htm");
//                    session.setAttribute("USER", loggedUser);
//                    mv = new ModelAndView(new RedirectView("/SpringMVC/messageHome.htm", false));
//
//                }
//                break;
//            case "register":
//                int regiesterUser = user.addUser(userName, password);
//                if (regiesterUser == 1) {
//                    Login loggeduser = new Login(userName, password);
//                    session.setAttribute("USER", loggeduser);
//                    mv = new ModelAndView(new RedirectView("/SpringMVC/messageHome.htm", false));
//                    //Redirecet to messages controller
//                } else {
//                    mv = new ModelAndView("loginPage");
//                }
//                break;
            case "quiz":
                return new ModelAndView("redirect:question/1.htm");
//            mv = new ModelAndView("quiz");

            
            default:
                mv = new ModelAndView("loginPage");
        }
        return mv;
    }
}
