/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chung-Yang Li
 */
public class Row {

    private String string1;
    private String string2;
    private String string3;
    private String string4;
    private String string5;
    private String string6;
    private String string7;
    private String string8;
    private String string9;
    private String string10;
    private String string11;
    private String string12;
    private String string13;
    private String string14;
    private String string15;
    private String string16;
    private String string17;
    private String string18;
    private String string19;
    private String string20;
    private String string21;
    private String string22;
    private String string23;
    private String string24;
    private String string25;

    public String getString4() {
        return string4;
    }

    public void setString4(String string4) {
        this.string4 = string4;
    }

    public String getString5() {
        return string5;
    }

    public void setString5(String string5) {
        this.string5 = string5;
    }

    public String getString6() {
        return string6;
    }

    public void setString6(String string6) {
        this.string6 = string6;
    }

    public String getString7() {
        return string7;
    }

    public void setString7(String string7) {
        this.string7 = string7;
    }

    public String getString8() {
        return string8;
    }

    public void setString8(String string8) {
        this.string8 = string8;
    }

    public String getString9() {
        return string9;
    }

    public void setString9(String string9) {
        this.string9 = string9;
    }

    public String getString10() {
        return string10;
    }

    public void setString10(String string10) {
        this.string10 = string10;
    }

    public String getString11() {
        return string11;
    }

    public void setString11(String string11) {
        this.string11 = string11;
    }

    public String getString12() {
        return string12;
    }

    public void setString12(String string12) {
        this.string12 = string12;
    }

    public String getString13() {
        return string13;
    }

    public void setString13(String string13) {
        this.string13 = string13;
    }

    public String getString14() {
        return string14;
    }

    public void setString14(String string14) {
        this.string14 = string14;
    }

    public String getString15() {
        return string15;
    }

    public void setString15(String string15) {
        this.string15 = string15;
    }

    public String getString16() {
        return string16;
    }

    public void setString16(String string16) {
        this.string16 = string16;
    }

    public String getString17() {
        return string17;
    }

    public void setString17(String string17) {
        this.string17 = string17;
    }

    public String getString18() {
        return string18;
    }

    public void setString18(String string18) {
        this.string18 = string18;
    }

    public String getString19() {
        return string19;
    }

    public void setString19(String string19) {
        this.string19 = string19;
    }

    public String getString20() {
        return string20;
    }

    public void setString20(String string20) {
        this.string20 = string20;
    }

    public String getString21() {
        return string21;
    }

    public void setString21(String string21) {
        this.string21 = string21;
    }

    public String getString22() {
        return string22;
    }

    public void setString22(String string22) {
        this.string22 = string22;
    }

    public String getString23() {
        return string23;
    }

    public void setString23(String string23) {
        this.string23 = string23;
    }

    public String getString24() {
        return string24;
    }

    public void setString24(String string24) {
        this.string24 = string24;
    }

    public String getString25() {
        return string25;
    }

    public void setString25(String string25) {
        this.string25 = string25;
    }

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

    public String getString1() {
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
            row.setString3(rs.getString(3));
            row.setString4(rs.getString(4));
            row.setString5(rs.getString(5));
            row.setString6(rs.getString(6));
            row.setString7(rs.getString(7));
            row.setString8(rs.getString(8));
            row.setString9(rs.getString(9));
            row.setString10(rs.getString(10));
            row.setString11(rs.getString(11));
            row.setString12(rs.getString(12));
            row.setString13(rs.getString(13));
            row.setString14(rs.getString(14));
            row.setString15(rs.getString(15));
            row.setString16(rs.getString(16));
            row.setString17(rs.getString(17));
            row.setString18(rs.getString(18));
            row.setString19(rs.getString(19));
            row.setString20(rs.getString(20));
            row.setString21(rs.getString(21));
            row.setString22(rs.getString(22));
            row.setString23(rs.getString(23));
            row.setString24(rs.getString(24));
            row.setString25(rs.getString(25));
            
            rows.add(row);
        };
        return rows;
    }


    
}
