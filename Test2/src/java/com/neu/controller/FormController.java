/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.pojo.Answer;
import com.neu.pojo.Question;
import com.neu.pojo.Quiz;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

/**
 *
 * @author Chung-Yang Li
 */
public class FormController extends AbstractWizardFormController{
    
    public FormController() {
		setCommandName("quizForm");
                setCommandClass(Answer.class);
                setValidator(new AnswersValidator());
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
		throws Exception {
//		List<Question> quiz = new ArrayList<>();

                return new Answer();
//		return new User();
//                return new Question(METHOD_GET, METHOD_GET, METHOD_GET, METHOD_GET, METHOD_GET);
	}
        
        
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
		HttpServletResponse response, Object command, BindException errors)
		throws Exception {
		
		//Get the data from command object
		Answer answer = (Answer)command;
//		System.out.println(answer.getA1());
//                System.out.println(answer.getA2());
//                System.out.println(answer.getA3());
//                System.out.println(answer.getA4());
//                System.out.println(answer.getA5());
//                System.out.println(answer.getA6());
                
                
		//where is the finish page?
		return new ModelAndView("ResultForm", "answer", answer);
	}

	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
		HttpServletResponse response, Object command, BindException errors)
		throws Exception {
		
		//where is the cancel page?
		return new ModelAndView("loginPage");
	}
        
        protected ModelAndView showForm(
			HttpServletRequest request, HttpServletResponse response, BindException errors)
	    throws Exception {
                String p = request.getParameter("page") == null? "0":request.getParameter("page");
//                System.out.println(p);
                int q = Integer.parseInt(p);
                q++;
                
//                System.out.println(getPageCount());
//                System.out.println("?");
//                System.out.println(q);
                Quiz quiz = Quiz.getInstance();
                List<Question> questions= quiz.getQuiz();
		request.setAttribute("question", questions.get(q));
                request.setAttribute("q", q);
		return showPage(request, errors, getInitialPage(request, errors.getTarget()));
	}
        
//        @Override
//        protected  ModelAndView	processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
//        }
        
        protected  void	postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) {
//            System.out.println(errors.getErrorCount());
            HttpSession session = request.getSession();
            String answerNum = request.getParameter("ans")==null? "": request.getParameter("ans");
            
            String p = request.getParameter("page") == null? "0":request.getParameter("page");

            int q = Integer.parseInt(p);
                
                
              
                
            if(request.getParameter("_target0").equals("Next")){
                AnswersValidator validator = (AnswersValidator) getValidator();
                validator.validateForm(command, errors, q);
                if(errors.getErrorCount()==0){q++;}
            }
            else if (request.getParameter("_target0").equals("Previous")){q--;}
                
//                if(request.getParameter("_target0").equals("Next")){q++;}
//                else if (request.getParameter("_target0").equals("Previous")){q--;}

                Quiz quiz = Quiz.getInstance();
                List<Question> questions= quiz.getQuiz();
		request.setAttribute("question", questions.get(q));
                request.setAttribute("q", q);
                
                session.setAttribute("answer" + String.valueOf(q-1), answerNum);
        }
        
//        @Override
//	protected void validatePage(Object command, Errors errors, int page) {
////                System.out.println(page);
//		AnswersValidator validator = (AnswersValidator) getValidator();
//		validator.validatePage1Form(command, errors);
//		//page is 0-indexed
////		switch (page) {
////		   case 0: //if page 1 , go validate with validatePage1Form
////			validator.validatePage1Form(command, errors);
////			break;
////		   case 1: //if page 2 , go validate with validatePage2Form
////			validator.validatePage2Form(command, errors);
////			break;
////		   case 2: //if page 3 , go validate with validatePage3Form
////			validator.validatePage3Form(command, errors);
////			break;
////		}
//	}
        
}
