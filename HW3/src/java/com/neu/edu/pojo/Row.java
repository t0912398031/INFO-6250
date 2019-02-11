/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chung-Yang Li
 */
public class Row {

    private static String string1;
    private static String string2;
    private String string3;

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public static String getString1() {
        return string1;
    }
    
    

    public void setString1(String string1) {
        this.string1 = string1;
    }



    public static List<Row> list(ResultSet rs) throws SQLException {
        List<Row> rows = new ArrayList<Row>();
        while (rs.next()) {
            Row row = new Row();
            row.setString1(rs.getString(1));
            row.setString2(rs.getString(2));
            rows.add(row);
        };
        return rows;
    }


    
}
