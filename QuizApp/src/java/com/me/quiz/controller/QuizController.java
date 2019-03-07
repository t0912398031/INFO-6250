/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.quiz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author ysf
 */
public class QuizController extends AbstractController {
    
    public QuizController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        String q;
        String answer;
        
        if (uri.endsWith("1.htm"))
        {
            //question 1 displayed, no answer to read
            q = "1";  //tell the view page to display Question 1
        } else if (uri.endsWith("2.htm"))
        {
            //question 1 submitted - read question 1
            q = "2";
        }else if (uri.endsWith("3.htm"))
        {
            //question 2 submitted - read question 2
            q = "3";
        } else if (uri.endsWith("4.htm"))
        {
            //question 3 submitted - read question 3. assuming there are 3 questions, display answers
            q = "answers";
        }else {
            //first time user is visiting, so display the 1st question
            return new ModelAndView("redirect:question/1.htm");
        }
        return new ModelAndView("quiz-view", "q", q);
    }
    
}
