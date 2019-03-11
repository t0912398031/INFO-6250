package com.neu.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.neu.pojo.Answer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author KW
 */
public class AnswersValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Answer.class.isAssignableFrom(clazz);
    }
    public void validateForm(Object target, Errors errors, int q) {
        switch (q) {
            case 1:
                 ValidationUtils.rejectIfEmpty(errors, "a1", "a1.req", "Please select one option 1.");
                 break;
            case 2:
                 ValidationUtils.rejectIfEmpty(errors, "a2", "a2.req", "Please select one option 2.");
                 break;
            case 3:
                 ValidationUtils.rejectIfEmpty(errors, "a3", "a3.req", "Please select one option 3.");
                 break;
            case 4:
                 ValidationUtils.rejectIfEmpty(errors, "a4", "a4.req", "Please select one option 4.");
                 break;
            case 5:
                 ValidationUtils.rejectIfEmpty(errors, "a5", "a5.req", "Please select one option 5.");
                 break;
            case 6:
                 ValidationUtils.rejectIfEmpty(errors, "a6", "a6.req", "Please select one option 6.");
                 break;
            case 7:
                 ValidationUtils.rejectIfEmpty(errors, "a7", "a7.req", "Please select one option 7.");
                 break;
            case 8:
                 ValidationUtils.rejectIfEmpty(errors, "a8", "a8.req", "Please select one option 8.");
                 break;
            case 9:
                 ValidationUtils.rejectIfEmpty(errors, "a9", "a9.req", "Please select one option 9.");
                 break;
            case 10:
                 ValidationUtils.rejectIfEmpty(errors, "a10", "a10.req", "Please select one option 10.");
                 break;
        }
        
//        else if(a.getA2()==null){ValidationUtils.rejectIfEmpty(errors, "a2", "a2.req", "Please select one option 2.");}
//        ValidationUtils.rejectIfEmpty(errors, "a1", "a1.req", "Please select one option.");
//        ValidationUtils.rejectIfEmpty(errors, "a2", "a2.req", "Please select one option.");
//        ValidationUtils.rejectIfEmpty(errors, "a3", "a3.req", "Please select one option.");

    }   
    public void validatePage1Form(Object target, Errors errors) {
        Answer a = (Answer)target;
        if(a.getA1()==null){ValidationUtils.rejectIfEmpty(errors, "a1", "a1.req", "Please select one option 1.");}
//        else if(a.getA2()==null){ValidationUtils.rejectIfEmpty(errors, "a2", "a2.req", "Please select one option 2.");}
//        ValidationUtils.rejectIfEmpty(errors, "a1", "a1.req", "Please select one option.");
//        ValidationUtils.rejectIfEmpty(errors, "a2", "a2.req", "Please select one option.");
//        ValidationUtils.rejectIfEmpty(errors, "a3", "a3.req", "Please select one option.");

    }

//    public void validatePage2Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a2", "a2.req", "Please select one option.");
//    }

//    public void validatePage3Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a3", "a3.req", "Please select one option.");
//    }
//
//    public void validatePage4Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a4", "a4.req", "Please select one option.");
//    }
//
//    public void validatePage5Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a5", "a5.req", "Please select one option.");
//    }

//    public void validatePage6Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a6", "a6.req", "Please select one option.");
//    }
//
//    public void validatePage7Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a7", "a7.req", "Please select one option.");
//    }
//
//    public void validatePage8Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a8", "a8.req", "Please select one option.");
//    }

//    public void validatePage9Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a9", "a9.req", "Please select one option.");
//    }
//
//    public void validatePage10Form(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "a10", "a10.req", "Please select one option.");
//    }

    @Override
    public void validate(Object o, Errors errors) {
        validatePage1Form(o, errors);
//        validatePage2Form(o, errors);
//        validatePage3Form(o, errors);
//        validatePage4Form(o, errors);
//        validatePage5Form(o, errors);
//        validatePage6Form(o, errors);
//        validatePage7Form(o, errors);
//        validatePage8Form(o, errors);
//        validatePage9Form(o, errors);
//        validatePage10Form(o, errors);
    }

}
