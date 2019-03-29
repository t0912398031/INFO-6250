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
public class BitCoin {

    private long ID;
    private String bitcoinID;
    private String owner;
    private User user;

    public BitCoin(String bitcoinID) {
        this.bitcoinID = bitcoinID;

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getBitcoinID() {
        return bitcoinID;
    }

    public void setBitcoinID(String bitcoinID) {
        this.bitcoinID = bitcoinID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
