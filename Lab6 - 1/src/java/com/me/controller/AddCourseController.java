/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.CourseDao;
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
public class AddCourseController extends AbstractController {
    
    public AddCourseController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("USER") == null){
            return new ModelAndView(new RedirectView("user.htm", false));
        }
        
        String username = (String) session.getAttribute("USER");
        String coursename = request.getParameter("coursename");
        String courseCRN = request.getParameter("courseCRN");
        String requestType = request.getParameter("formtype");
        
        CourseDao courseDao = (CourseDao) getApplicationContext().getBean("courseDao");
        
        if(requestType.equals("submitcourse")){
            if(courseDao.addCourse(coursename, courseCRN, username)){
                return new ModelAndView("add-success");
            } else{
                return new ModelAndView("error");
            }
        } else if(requestType.equals("home")){
            return new ModelAndView("user-success");
        } else{
            return new ModelAndView("addCourse");
        }
    }
    
}
