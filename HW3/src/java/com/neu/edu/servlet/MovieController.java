/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;
/**
 *
 * @author Chung-Yang Li
 */
import com.neu.edu.dao.MovieDao;
import com.neu.edu.pojo.Movie;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String option = req.getParameter("option");
        RequestDispatcher requestDispatcher = null;
//        System.out.println(option);


        if (option.equals("browse")) {
            MovieDao md = new MovieDao();
            List<Movie> movies = md.getMovies();
            req.setAttribute("movies", movies);
            requestDispatcher = req.getRequestDispatcher("movie.jsp");
            requestDispatcher.forward(req, resp);
            

        }  else if (option.equals("add")) {

            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addMovie.jsp");
            requestDispatcher.forward(req, resp);
            

        }   else if (option.equals("insert")) {
            String title = req.getParameter("title");
            String actor = req.getParameter("actor");
            String actress = req.getParameter("actress");
            String genre = req.getParameter("genre");
            int year = Integer.parseInt(req.getParameter("year"));
            
            MovieDao md = new MovieDao();
            int result = md.addMovies(title, actor, actress, genre, year);
            if (result == 1) {
//                System.out.println("successful");
                req.setAttribute("status", "Add Successfully");
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addMovie.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                req.setAttribute("status", "Error");
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addMovie.jsp");
                requestDispatcher.forward(req, resp);
            }
//            
//            
//            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addMovie.jsp");
//            requestDispatcher.forward(req, resp);
//            

        }  
        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        RequestDispatcher requestDispatcher = null;
//        
//        req.setAttribute("option", "book");
//        List<String> item = Arrays.asList("java", "oracle", "uml", "object", "web");
//        req.setAttribute("items", item);
//        requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopping.jsp");
//        requestDispatcher.forward(req, resp);

    }

}
