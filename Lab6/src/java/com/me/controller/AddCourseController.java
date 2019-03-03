/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.neu.dao.CourseDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Adi
 */
public class AddCourseController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        HttpSession session = hsr.getSession();
        if (session.getAttribute("USER") == null) {
            return new ModelAndView(new RedirectView("user.htm", false));
        }
        String username=(String)session.getAttribute("USER");
        String requestType = (String) hsr.getParameter("formtype");
        System.out.println("requestType "+requestType);
        if(requestType.equals("submitcourse")) {
            String coursename = hsr.getParameter("coursename");
            String courseCRN = hsr.getParameter("courseCRN");
            CourseDAO courseDao = (CourseDAO) getApplicationContext().getBean("coursedao");
            if(courseDao.addCourse(coursename, courseCRN,username)) {
                return new ModelAndView("add-success");
            } else {
                return new ModelAndView("error");
            }
        } else if(requestType.equals("home")) {
            return new ModelAndView("user-success");
        }else {
            return new ModelAndView("addcourse");
        }
    }    
}
