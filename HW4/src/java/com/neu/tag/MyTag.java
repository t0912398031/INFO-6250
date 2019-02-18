/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.tag;
import com.neu.pojo.Row;
import java.io.StringWriter;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
/**
 *
 * @author Chung-Yang Li
 */
public class MyTag extends SimpleTagSupport {
    private String message;

   public void setMessage(String msg) {
      this.message = msg;
   }
   StringWriter sw = new StringWriter();
   public void doTag()
   
   throws JspException, IOException {
      if (message != null) {
          
          
        try{
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        
            String path = "D:\\download\\Github\\INFO-6250\\HW4";

            Connection con = DriverManager.getConnection("jdbc:relique:csv:" + path);
            
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SalesOrderID, OrderDate FROM " + message);
            
              System.out.println(rs);

            List<Row> rows = Row.list(rs);

//            List<Row> rows = new ArrayList<Row>();
//            while (rs.next()) {
//                Row row = new Row();
//                row.setString1(rs.getString(1));
//                row.setString2(rs.getString(2));
//                rows.add(row);
//            }

            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
          
          
         /* Use message from attribute */
         JspWriter out = getJspContext().getOut();
         out.println( message );
      } else {
         /* use message from the body */
         getJspBody().invoke(sw);
         getJspContext().getOut().println(sw.toString());
      }
   }
}
