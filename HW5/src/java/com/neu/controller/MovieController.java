/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.dao.MovieDao;
import com.neu.dao.UserDao;
import com.neu.pojo.Login;
import com.neu.pojo.Movie;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
public class MovieController extends AbstractController {

    public MovieController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        ModelAndView mv = null;

        

        
        
        String option = request.getParameter("option");
//        System.out.println(option);

        if (option.equals("browse")) {
//            MovieDao md = new MovieDao();
//            List<Movie> movies = md.getMovies();
//            request.setAttribute("movies", movies);
//            request.setAttribute("test", "test");
            request.setAttribute("type", "add");
//            mv = new ModelAndView("movie");
            return new ModelAndView(new RedirectView("movieform.htm", false));

//        }  else if (option.equals("add")) {
//            
//            request.setAttribute("type", "add");
//            return new ModelAndView(new RedirectView("movieform.htm", false));
////            mv = new ModelAndView("addMovie");

        }   else if (option.equals("search")) {
            request.setAttribute("type", "search");
            return new ModelAndView(new RedirectView("moviesearchform.htm", false));
        } 
        
        
        
        return mv;
    }
}
