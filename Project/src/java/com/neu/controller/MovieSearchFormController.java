/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.MovieDao;
import com.neu.pojo.Movie;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author adike
 */
public class MovieSearchFormController extends SimpleFormController {

    public MovieSearchFormController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Movie.class);
        setCommandName("moviesearch");
        setSuccessView("movie-success");
        setFormView("moviesearch-form");
    }

    @Override
    protected void doSubmitAction(Object command) throws Exception {
    }

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        HttpSession session = request.getSession();
        ModelAndView mv;
        Movie m = (Movie) command;
        MovieDao movieDao = (MovieDao) getApplicationContext().getBean("moviedao");
//        String id = (String)request.getAttribute("ID");
//        if(movieDao.addMovie(m)==1){
//            System.out.println("add succeed");
//        } else{
//            System.out.println("add fail");
//        }
//        
//        System.out.println(m.getActor());
//        List<Movie> movies = movieDao.getMovies();
//        User login = userDAO.authenticateLogin(u.getUsername(), u.getPassword());
//        System.out.println(login);
//        if (login == null) {
//            mv = new ModelAndView("error");
//        } else {
//            session.setAttribute("USER", login.getUsername());
//            mv = new ModelAndView(getSuccessView());
//        }

        Movie movie = movieDao.searchMovie(String.valueOf(m.getId()));
        
        
        session.setAttribute("Type", "Search");
        
//        if (movies.size() == 0) {
//            mv = new ModelAndView("error", "message", "No matching users");
//        } else {
//            mv = new ModelAndView(getSuccessView(), "movie", movie);
//        }
        mv = new ModelAndView(getSuccessView(), "movie", movie);
        
//        mv = new ModelAndView(getSuccessView());
        return mv;
    }
}
