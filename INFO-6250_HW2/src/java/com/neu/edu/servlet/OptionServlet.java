/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hardik
 */
public class OptionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String option = req.getParameter("option");
//        String userName = req.getParameter("userName");
//        String password = req.getParameter("password");
        String redirectPage = "index.html";

        java.sql.Connection connection = null;
        Statement stmt = null;
        // Load the driver and make cconnection
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://newton.neu.edu:3306/moviedb", "student", "p@ssw0rd");
            stmt = connection.createStatement();
        }
        
        catch (ClassNotFoundException ex){
            Logger.getLogger(OptionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch (SQLException ex){
            Logger.getLogger(OptionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (option.equals("browse")) {
//            redirectPage = "/INFO-6250_HW2/browse";
     
            String query = "SELECT * FROM movies";
            System.out.println(query);
        try {
            ResultSet rs = stmt.executeQuery(query);
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>movie</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<table>"
                    +"<thead>"
                    +"<th>title</th>"
                    +"<th>actor</th>"
                    +"<th>actress</th>"
                    +"<th>genre</th>"
                    +"<th>year</th>"
                    +"</thead>"
                    +"<tbody>");
            
            while (rs.next()) {
                System.out.println(rs.getString("title"));
                System.out.println(rs.getString("actor"));
                System.out.println(rs.getString("actress"));
                System.out.println(rs.getString("genre"));
                System.out.println(rs.getString("year"));


                out.println("<tr>"
                        +"<td>" + rs.getString("title") + "</td>"
                        +"<td>" + rs.getString("actor") + "</td>"
                        +"<td>" + rs.getString("actress") + "</td>"
                        +"<td>" + rs.getString("genre") + "</td>"
                        +"<td>" + rs.getString("year") + "</td>"            
                        );
//                Login login=new Login();
//                login.setUsername(rs.getString("UserName"));
//                users.add(login);
                       
            } 
            out.println("<a href='movieStore.html'>Back to movie store.</a><br>");
            out.println("</body>");
            out.println("</html>"); 
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException" + ex.getMessage());
            }
        }
            
        } else if (option.equals("add")){
            redirectPage = "addMovies.html";
            resp.sendRedirect(redirectPage);
//            // Check if user exists then create and add user to session
//            
//            // Insert query string
//            String sqlQuery = "INSERT INTO userstable (UserName, UserPassword)"
//                                + "VALUES ('" + userName + "', '" + password + "')";
//            System.out.println(sqlQuery);
//            
//            try {
//                int rs = stmt.executeUpdate(sqlQuery);    
//                if (rs != 0) {
//                    System.out.println(rs);
//                    session.setAttribute("userName", userName);
////                    redirectPage = "/INFO-6250_HW2/message";
//                }
//                
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            } finally {
//                try {
//                    if (stmt != null) {
//                        stmt.close();
//                    }
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException ex) {
//                    System.out.println("SQLException" + ex.getMessage());
//                }
//            }
        }
//        resp.sendRedirect(redirectPage);
    }
}
