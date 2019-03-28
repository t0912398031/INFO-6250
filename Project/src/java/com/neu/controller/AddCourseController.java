/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.CourseDao;
import com.neu.pojo.Login;
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
public class AddCourseController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        HttpSession session = hsr.getSession();
        ModelAndView mv = null;
        Login loggedUser = (Login) session.getAttribute("USER");
        if (loggedUser == null) {
            hsr1.sendRedirect("/");
        } else {
            String username = loggedUser.getUsername();
            String requestType = (String) hsr.getParameter("formtype");
            System.out.println("requestType " + requestType);
            if (requestType.equals("submitcourse")) {
                String coursename = hsr.getParameter("coursename");
                String courseCRN = hsr.getParameter("courseCRN");
                CourseDao courseDao = (CourseDao) getApplicationContext().getBean("couseDAO");
                if (courseDao.addCourse(coursename, courseCRN, username) == 1) {
                    mv = new ModelAndView("add-success");
                } else {
                    mv = new ModelAndView("error");
                }
            } else if (requestType.equals("home")) {
                mv =  new ModelAndView("messageHome");
            } else if (requestType.equals("addcourse")) {
                mv =  new ModelAndView("addcourse");
            }
            //mv =  new ModelAndView("error");
        }
        return mv;
    }
}
