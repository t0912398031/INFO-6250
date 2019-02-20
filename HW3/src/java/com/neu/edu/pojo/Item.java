/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.pojo;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Chung-Yang Li
 */
public class Item {
    private String type;
    private List<String> book = Arrays.asList("java", "oracle", "uml", "object", "web");
    private List<String> laptop = Arrays.asList("apple", "asus", "hp", "toshiba", "sony");
    private List<String> cd = Arrays.asList("mandomna", "spears", "justin", "nelly", "kanye");

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getBook() {
        return book;
    }

    public List<String> getLaptop() {
        return laptop;
    }

    public List<String> getCd() {
        return cd;
    }
    
    public List<String> getItemByType() {
        if (type.equals("book")){
            return book;
        } else if (type.equals("laptop")){
            return laptop;
        } else if (type.equals("cd")){
            return cd;
        }
        return null;
    }
    

    
}
