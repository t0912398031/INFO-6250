/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.CourseDao;
import com.me.pojo.Course;
import java.util.List;
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
public class SearchCourseController extends AbstractController {
    
    public SearchCourseController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("USER") == null){
            return new ModelAndView(new RedirectView("user.htm", false));
        }
        String username = (String) session.getAttribute("USER");
        String searchString = request.getParameter("searchBar");
        String searchType = request.getParameter("courseType"); 
        
        CourseDao courseDao = (CourseDao) getApplicationContext().getBean("courseDao");
        List<Course> courses = courseDao.getCourses(searchString, searchType, username);
        
        return new ModelAndView("viewcourse", "courses", courses);
    }
    
}
