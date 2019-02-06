package com.neu.edu.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CSVServlet extends HttpServlet {
    //Service method
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
   
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        
        try{
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        
            String path = "D:\\download\\Github\\INFO-6250_HW2";

            Connection con = DriverManager.getConnection("jdbc:relique:csv:" + path);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SalesOrderID, OrderDate FROM SalesOrder");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Books</title>");
            out.println("</head>");
            out.println("<body>");
            while(rs.next()){
                out.println("<div>" + rs.getString(1) + "\t" + rs.getString(2) + "</div>");
                
                
                
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
            out.println("</body>");
            out.println("</html>");
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
}