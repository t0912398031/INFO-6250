/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.pojo;

import java.util.Random;

public class RandomBean {
    private static final Random RANDOM = new Random();

    public int getNextInt() {
        return RANDOM.nextInt(10);
    }
}
