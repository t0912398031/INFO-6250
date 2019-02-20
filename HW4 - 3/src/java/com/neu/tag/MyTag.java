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

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + message);
            
//              System.out.println(rs);

            List<Row> rows = Row.list(rs);

//            List<Row> rows = new ArrayList<Row>();
//            while (rs.next()) {
//                Row row = new Row();
//                row.setString1(rs.getString(1));
//                row.setString2(rs.getString(2));
//                rows.add(row);
//            }
            
            JspWriter out = getJspContext().getOut();
            out.println("<table>"); 
            for(Row r: rows){
                out.println("<tr>");                
                out.println("<td>" + r.getString1() + "</td>");
                out.println("<td>" + r.getString2() + "</td>");
                out.println("<td>" + r.getString3() + "</td>");
                out.println("<td>" + r.getString4() + "</td>");
                out.println("<td>" + r.getString5() + "</td>");
                out.println("<td>" + r.getString6() + "</td>");
                out.println("<td>" + r.getString7() + "</td>");
                out.println("<td>" + r.getString8() + "</td>");
                out.println("<td>" + r.getString9() + "</td>");
                out.println("<td>" + r.getString10() + "</td>");
                out.println("<td>" + r.getString11() + "</td>");
                out.println("<td>" + r.getString12() + "</td>");
                out.println("<td>" + r.getString13() + "</td>");
                out.println("<td>" + r.getString14() + "</td>");
                out.println("<td>" + r.getString15() + "</td>");
                out.println("<td>" + r.getString16() + "</td>");
                out.println("<td>" + r.getString17() + "</td>");
                out.println("<td>" + r.getString18() + "</td>");
                out.println("<td>" + r.getString19() + "</td>");
                out.println("<td>" + r.getString20() + "</td>");
                out.println("<td>" + r.getString21() + "</td>");
                out.println("<td>" + r.getString22() + "</td>");
                out.println("<td>" + r.getString23() + "</td>");
                out.println("<td>" + r.getString24() + "</td>");
                out.println("<td>" + r.getString25() + "</td>");
                out.println("</tr>");  
            }
            out.println("</table>"); 
            
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
          
          
         /* Use message from attribute */
//         JspWriter out = getJspContext().getOut();
//         out.println( message);
      } else {
         /* use message from the body */
         getJspBody().invoke(sw);
         getJspContext().getOut().println(sw.toString());
      }
   }
}
