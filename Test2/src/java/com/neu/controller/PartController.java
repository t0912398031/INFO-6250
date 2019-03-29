/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

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
