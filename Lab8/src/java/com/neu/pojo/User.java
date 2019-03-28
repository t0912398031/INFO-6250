/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

import java.util.Set;

/**
 *
 * @author Adi
 */
public class User {
    private long userID;
    private String fname;
    private String lname;
    private Set phNum;

    public User() {
    }

    public Set getPhNum() {
        return phNum;
    }

    public void setPhNum(Set phNum) {
        this.phNum = phNum;
    }
    
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
