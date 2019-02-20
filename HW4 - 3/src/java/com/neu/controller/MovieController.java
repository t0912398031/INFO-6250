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
            MovieDao md = new MovieDao();
            List<Movie> movies = md.getMovies();
            request.setAttribute("movies", movies);
//            request.setAttribute("test", "test");
            mv = new ModelAndView("movie");

        }  else if (option.equals("add")) {
            
            mv = new ModelAndView("addMovie");

        }   else if (option.equals("insert")) {
            String title = request.getParameter("title");
            String actor = request.getParameter("actor");
            String actress = request.getParameter("actress");
            String genre = request.getParameter("genre");
            int year = Integer.parseInt(request.getParameter("year"));
            
            MovieDao md = new MovieDao();
            int result = md.addMovies(title, actor, actress, genre, year);
            if (result == 1) {
//                System.out.println("successful");
                request.setAttribute("status", "Add Successfully");

                mv = new ModelAndView("addMovie");
            } else {
//                request.setAttribute("status", "Error");
//                requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addMovie.jsp");
//                requestDispatcher.forward(req, resp);
                request.setAttribute("status", "fail");
                mv = new ModelAndView("error");
            }
//            
//            
//            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addMovie.jsp");
//            requestDispatcher.forward(req, resp);
//            

        } 
        
        
        
        return mv;
    }
}
