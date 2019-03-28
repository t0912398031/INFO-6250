/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.dao.BitCoinDao;
import com.neu.pojo.Login;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author harsh
 */
public class PartController extends AbstractController {

    public PartController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv = null;
        
        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
        System.out.println(option);
        switch (option) {

            case "movie":
                mv = new ModelAndView("movie");
                break;

            case "quiz":
                return new ModelAndView("redirect:question/1.htm");

            case "form":
                return new ModelAndView("redirect:form.htm");
            
            default:
                mv = new ModelAndView("loginPage");
        }
        return mv;
    }
}
