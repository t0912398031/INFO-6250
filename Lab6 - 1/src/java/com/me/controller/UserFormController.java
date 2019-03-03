/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.UserDao;
import com.me.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Chung-Yang Li
 */
public class UserFormController extends SimpleFormController {
    
    public UserFormController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(User.class);
        setCommandName("user");
        setSuccessView("user-success");
        setFormView("user-form");
    }
    
//    @Override
//    protected void doSubmitAction(Object command) throws Exception {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    @Override
    protected ModelAndView onSubmit(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object command, 
            BindException errors) throws Exception {
        
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        User u = (User) command;
        UserDao userDao = (UserDao)getApplicationContext().getBean("userDao");
        User login = userDao.authenticateLogin(u.getUsername(), u.getPassword());
        if(login == null){
            mv = new ModelAndView("error");
        } else{
            session.setAttribute("USER", login.getUsername());
            mv = new ModelAndView(getSuccessView());
        }
        //Do something...
        return mv;
    }
     
}
