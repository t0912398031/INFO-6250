/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu;

import com.neu.Dao.UserDao;
import com.neu.pojo.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Chung-Yang Li
 */
public class AuthenticationController extends AbstractController {
    
    public AuthenticationController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        
        String option = request.getParameter("option") == null? "":request.getParameter("option");
        if(session.getAttribute("USER") == null && option.equals("")){
            return new ModelAndView("loginPage");

        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userdao = new UserDao();
        switch(option){
            case "login":
                Login loggedUser = userdao.authenticateLogin(username, password);
                if(loggedUser == null){
                    return new ModelAndView("loginPage");
                } else{
                    session.setAttribute("USER", loggedUser);
                    mv = new ModelAndView(new RedirectView("/SpringApp/massageHome.htm", false));
                }
            case "logout":
                session.invalidate();
                mv = new ModelAndView("loginPage");
                break;
            case "register":
                int regiesterUser = userdao.addUser(username, password);
                if (regiesterUser == 1) {
                    Login loggeduser = new Login(username, password);
                    session.setAttribute("USER", loggeduser);
                    mv = new ModelAndView(new RedirectView("/SpringApp/massageHome.htm", false));
                } else{
                    mv = new ModelAndView("loginPage");
                }
                break;
            default:
                mv = new ModelAndView(new RedirectView("/SpringApp/massageHome.htm", false));
        }
        
        return mv;
    }
    
}
