/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

/**
 *
 * @author Adi
 */
public class Phone {

    private long phoneID;
    private String phoneNumber;
    private User user;

    public Phone(String phoneNumber) {
        this.phoneID = phoneID;
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(long phoneID) {
        this.phoneID = phoneID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
