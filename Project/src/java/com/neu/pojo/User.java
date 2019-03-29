/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

import java.util.Set;

/**
 *
 * @author Chung-Yang Li
 */
public class User {
    private long userID;
    private String userName;
    private String password;
    private String name; 
    private int balance;
    private Set<BitCoin> bitCoins;
    private Set record;
    private Set order;

    public User() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BitCoin> getBitCoins() {
        return bitCoins;
    }

    public void setBitCoins(Set<BitCoin> bitCoins) {
        this.bitCoins = bitCoins;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Set getRecord() {
        return record;
    }

    public void setRecord(Set record) {
        this.record = record;
    }

    public Set getOrder() {
        return order;
    }

    public void setOrder(Set order) {
        this.order = order;
    }

}
