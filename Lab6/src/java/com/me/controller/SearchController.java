/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.pojo.Course;
import com.neu.dao.CourseDAO;
import java.util.List;
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
public class SearchController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        HttpSession session = hsr.getSession();
        if (session.getAttribute("USER") == null) {
            return new ModelAndView(new RedirectView("user.htm", false));
        }
        String username=(String)session.getAttribute("USER");
        String searchString = hsr.getParameter("searchBar");
        String searchType = hsr.getParameter("courseType");
        CourseDAO courseDao = (CourseDAO) getApplicationContext().getBean("coursedao");
        List<Course> courses= courseDao.getCourses(searchString, searchType,username);
        
        return new ModelAndView("viewcourse","courses",courses);
        
    }
}
