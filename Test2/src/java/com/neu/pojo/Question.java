/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Chung-Yang Li
 */
public class Question {
    
    private String q1;
    private String q2;  
    private String q3;
    private String q4;  
    private String question;
    
    public Question(String q1, String q2, String q3, String q4, String question) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getQ1() {
        return q1;
    }

    public String getQ2() {
        return q2;
    }

    public String getQ3() {
        return q3;
    }

    public String getQ4() {
        return q4;
    }
    
}
