/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.pojo.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hardik
 */
public class AddBookServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        
        int number = Integer.parseInt(req.getParameter("number"));
        String[] isbn = req.getParameterValues("isbn");
        String[] title = req.getParameterValues("title");
        String[] authors = req.getParameterValues("authors");
        String[] price = req.getParameterValues("price");
        Float[] p = Arrays.stream(price).map(Float::valueOf).toArray(Float[]::new);

        
        
        Connection connection = null;
        Statement stmt = null;
  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://newton.neu.edu:3306/booksdb", "student", "p@ssw0rd");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        }

        
        
        String updateTableSQL = "INSERT INTO books VALUES (?, ?, ?, ?)";
        
        
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
        
            for(int i=0; i<number; i++){
                preparedStatement.setString(1, isbn[i]);
                preparedStatement.setString(2, title[i]);
                preparedStatement.setString(3, authors[i]);
                preparedStatement.setFloat(4, p[i]);
                
                // execute insert SQL stetement
                preparedStatement.executeUpdate();
            }
            System.out.println("Inserted Successfully.");
            out.println("<h3>Inserted Successfully.</h3>");
        } catch (SQLException ex) {
            Logger.getLogger(AddBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
}
