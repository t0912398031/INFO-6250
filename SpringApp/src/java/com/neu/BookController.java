/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Chung-Yang Li
 */
public class BookController extends AbstractController {
    
    public BookController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ArrayList books = new ArrayList();
        books.add("java");
        books.add("C++");
        books.add("PHP");
        books.add("ASP");
        
        return new ModelAndView("bookview", "books", books);
//        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
