/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;


import com.neu.dao.CourseDao;
import com.neu.pojo.Course;
import com.neu.pojo.Login;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author rahulzore
 */
public class SearchController extends AbstractController {
    
    public SearchController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        Login loggedUser = (Login) session.getAttribute("USER");
        if(loggedUser == null){
            response.sendRedirect("/");
        } else {
        String username=loggedUser.getUsername();
        String searchString = request.getParameter("searchBar");
        String searchType = request.getParameter("courseType");
        CourseDao courseDao = (CourseDao) getApplicationContext().getBean("couseDAO");
        List<Course> courses = courseDao.getCourses(searchString, searchType, username);
        mv= new ModelAndView("viewcourse", "courses", courses);
        }
        return mv;
    }
    
}
