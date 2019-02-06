/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chung-Yang Li
 */
public class CSV {
    public static void main(String[] args) throws SQLException {
        
        try{
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        
            String path = "D:\\download\\Github\\INFO-6250_HW2";

            Connection con = DriverManager.getConnection("jdbc:relique:csv:" + path);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SalesOrderID, OrderDate FROM SalesOrder");

            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
}
