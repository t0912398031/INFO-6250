/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.pojo.Question;
import com.neu.pojo.Quiz;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String answerNum = request.getParameter("ans")==null? "": request.getParameter("ans");
        String answer;
        String q;

        Quiz quiz = Quiz.getInstance();
        List<Question> questions= quiz.getQuiz();
        
        
        if (uri.endsWith("1.htm")&& !uri.endsWith("11.htm"))
        {
            //question 1 displayed, no answer to read
//            System.out.println(questions.get(0));
            q = "1";  //tell the view page to display Question 1
        } else if (uri.endsWith("2.htm"))
        {
            //question 1 submitted - read question 1
//            System.out.println(questions.get(1));
            q = "2";
        }else if (uri.endsWith("3.htm"))
        {
            //question 2 submitted - read question 2
            q = "3";
        } else if (uri.endsWith("4.htm"))
        {
            //question 3 submitted - read question 3. assuming there are 3 questions, display answers
            q = "4";
        }else if (uri.endsWith("5.htm"))
        {
            //question 2 submitted - read question 2
            q = "5";
        }else if (uri.endsWith("6.htm"))
        {
            //question 2 submitted - read question 2
            q = "6";
        }else if (uri.endsWith("7.htm"))
        {
            //question 2 submitted - read question 2
            q = "7";
        }else if (uri.endsWith("8.htm"))
        {
            //question 2 submitted - read question 2
            q = "8";
        }else if (uri.endsWith("9.htm"))
        {
            //question 2 submitted - read question 2
            q = "9";      
        } else if (uri.endsWith("10.htm"))
        {
            //question 3 submitted - read question 3. assuming there are 3 questions, display answers
            q = "10";
        } else if (uri.endsWith("11.htm"))
        {
            //question 3 submitted - read question 3. assuming there are 3 questions, display answers
            q = "answers";
            switch(answerNum){
                case "1": answer = questions.get(10).getQ1();break;
                case "2": answer = questions.get(10).getQ2();break;
                case "3": answer = questions.get(10).getQ3();break;
                case "4": answer = questions.get(10).getQ4();break;
                default : answer = "";break;
            }
            session.setAttribute("answer10", answer);
//            session.setAttribute("answer10", answer);
        }else {
            //first time user is visiting, so display the 1st question
            return new ModelAndView("redirect:question/1.htm");
        }
        if (!q.equals("answers")){
            
            switch(answerNum){
            case "1": answer = questions.get(Integer.parseInt(q)-1).getQ1();break;
            case "2": answer = questions.get(Integer.parseInt(q)-1).getQ2();break;
            case "3": answer = questions.get(Integer.parseInt(q)-1).getQ3();break;
            case "4": answer = questions.get(Integer.parseInt(q)-1).getQ4();break;
            default : answer = "";break;
        }
            System.out.println(answer);
            request.setAttribute("question", questions.get(Integer.parseInt(q)));
            session.setAttribute("answer" + String.valueOf(Integer.parseInt(q)-1), answer);
        }
        
        return new ModelAndView("quiz-view", "q", q);
    }
    
}
