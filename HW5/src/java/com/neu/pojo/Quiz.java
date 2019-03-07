/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Chung-Yang Li
 */
public class Quiz {
    
    private static Quiz single_instance = null;
    
    private List<Question> quiz;
    private Question quiz0;
    private Question quiz1;
    private Question quiz2;
    private Question quiz3;
    private Question quiz4;
    private Question quiz5;
    private Question quiz6;
    private Question quiz7;
    private Question quiz8;
    private Question quiz9;
    private Question quiz10;
    
    public Quiz() {
        this.quiz = new ArrayList<Question>();
//        this.quiz = Arrays.asList("java", "oracle", "uml", "object", "web");
        this.quiz0 = new Question("","","","","");
        this.quiz1 = new Question("servce", "init", "doGet", "doPost","Which lifecycle method make ready the servlet for garbge collection?");
        this.quiz2 = new Question("servce", "init", "doGet", "doPost","Which method does not exist in HttpServlet Class?");
        this.quiz3 = new Question("servlet", "servlet-mapping", "web-app", "servlet-mappings","Which tag of DD maps internal name of servlet to public URL pattern?");
        this.quiz4 = new Question("servlet", "servlet-mapping", "web-app", "servlet-mappings","Which ");
        this.quiz5 = new Question("a", "b", "c", "d","Which ");
        this.quiz6 = new Question("servlet", "servlet-mapping", "web-app", "servlet-mappings","Which ");
        this.quiz7 = new Question("servlet", "servlet-mapping", "web-app", "servlet-mappings","Which ");
        this.quiz8 = new Question("e", "f", "g", "h","Which ");
        this.quiz9 = new Question("servlet", "servlet-mapping", "web-app", "servlet-mappings","Which ");
        this.quiz10 = new Question("k", "l", "m", "n","Which ");
        
        quiz.add(quiz0);
        quiz.add(quiz1);
        quiz.add(quiz2);
        quiz.add(quiz3);
        quiz.add(quiz4);
        quiz.add(quiz5);
        quiz.add(quiz6);
        quiz.add(quiz7);
        quiz.add(quiz8);
        quiz.add(quiz9);
        quiz.add(quiz10);
    }

    public static Quiz getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Quiz(); 
  
        return single_instance; 
    } 
    
    public List<Question> getQuiz() {
        return quiz;
    }
    
    
}
