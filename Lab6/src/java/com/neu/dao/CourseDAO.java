/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.me.pojo.Course;
import com.me.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Adi
 */
public class CourseDAO {

    private BasicDataSource conn;

    public BasicDataSource getConn() {
        return conn;
    }

    public void setConn(BasicDataSource conn) {
        this.conn = conn;
    }

    public boolean addCourse(String coursename, String courseCRN,String username) throws SQLException {
        Connection connection = null;
        int result = 0;
        try {
            connection = conn.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "INSERT INTO courses(courseName, courseCRN, username) VALUES (?,?,?)";
            ResultSetHandler<Course> h = new BeanHandler<>(Course.class);
            result = queryRunner.update(connection, query, coursename, courseCRN, username);
            if (result == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

    public List<Course> getCourses(String searchString, String searchType,String username) throws SQLException {
        Connection connection = null;
        List<Course> result = null;
        try {
            connection = conn.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Course>> h = new BeanListHandler<>(Course.class);
            String query = "SELECT * FROM courses where username=? and "+searchType+" = ?";
            result = queryRunner.query(connection, query, h, username, searchString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }
}
