/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.dao.MessageDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.Login;
import com.neu.edu.pojo.Message;
import com.neu.edu.pojo.Row;
import java.io.IOException;
import java.sql.Connection;
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
public class CSVController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String foldername = req.getParameter("foldername");
        RequestDispatcher requestDispatcher = null;
        
        try{
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        
            String path = "D:\\download\\Github\\INFO-6250\\HW3\\";

            Connection con = DriverManager.getConnection("jdbc:relique:csv:" + path + foldername);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SalesOrderID, OrderDate FROM SalesOrder");
            
            req.setAttribute("resultSet", rs);
            

            List<Row> rows = Row.list(rs);

//            List<Row> rows = new ArrayList<Row>();
//            while (rs.next()) {
//                Row row = new Row();
//                row.setString1(rs.getString(1));
//                row.setString2(rs.getString(2));
//                rows.add(row);
//            }
            req.setAttribute("rows", rows);
            requestDispatcher = req.getRequestDispatcher("csv.jsp");
            requestDispatcher.forward(req, resp);

//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Books</title>");
//            out.println("</head>");
//            out.println("<body>");
//            while(rs.next()){
//                out.println("<div>" + rs.getString(1) + "\t" + rs.getString(2) + "</div>");
//                
//                
//                
////                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
//            }
//            out.println("</body>");
//            out.println("</html>");
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        
        
        
        
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Login loggeduser = (Login) session.getAttribute("USER");
//        if (loggeduser == null) {
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
//            requestDispatcher.forward(req, resp);
//        } else {
//            String sendTo = req.getParameter("toUser");
//            req.setAttribute("toUser", sendTo);
//            RequestDispatcher requestDispatcher = null;
//            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/sendMessage.jsp");
//            requestDispatcher.forward(req, resp);
//        }
    }

}
