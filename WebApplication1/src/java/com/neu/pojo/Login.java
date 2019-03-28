/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

/**
 *
 * @author Chung-Yang Li
 */
public class Login {
    
    private long id;
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "Login{" + "username=" + username + ", password=" + password + '}';
    }
}