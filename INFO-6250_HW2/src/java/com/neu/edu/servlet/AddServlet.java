/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.pojo.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class AddServlet extends HttpServlet{
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
        
        String title = req.getParameter("title");
        String actor = req.getParameter("actor");
        String actress = req.getParameter("actress");
        String genre = req.getParameter("genre");
        String year = req.getParameter("year");
//        HttpSession session = req.getSession();
//        String userName = (String)session.getAttribute("userName");
//        if(userName == null) {
//            System.out.println("username not valid : " + userName);
//            resp.sendRedirect("index.html");
//            return;
//        }
        
        java.sql.Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://newton.neu.edu:3306/moviedb", "student", "p@ssw0rd");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        }

            // Check if it is a valid user 
//        List<Login> users=new ArrayList<Login>();
        String query = "INSERT INTO movies (title, actor, actress, genre, year)"
                    + "VALUES ('" + title + "', '" + actor + "', '" + actress + "', '" + genre + "', '" + year + "')";
        System.out.println(query);
        try {
            int rs = stmt.executeUpdate(query);    
                if (rs != 0) {
                    System.out.println(rs);
//                    session.setAttribute("userName", userName);
//                    redirectPage = "/INFO-6250 HW2/message";
                }
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='movieStore.html'>Back to movie store.</a><br/>");
            out.println("<h3>Movie Added Successfully</h3>");
            
             
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
        
       
//       req.setAttribute("users", users);
//       RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/listUsers.jsp");
//       requestDispatcher.forward(req, resp);
    }
    
}
