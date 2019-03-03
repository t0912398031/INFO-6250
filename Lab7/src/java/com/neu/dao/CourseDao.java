/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.neu.pojo.Course;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Chung-Yang Li
 */
public class CourseDao {
    private Session session = null;
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    
    private Session getSession(){
        if(session==null || !session.isOpen()){
            session = sf.openSession();
            
        }
        return session;
    }
    
    private void beginTransaction(){
        getSession().beginTransaction();
    }
    
    private void close(){
        if(session!=null){
            getSession().close();            
        }
    }
    
    private void commit(){
        getSession().getTransaction().commit();
    }

    private void rollbackTransaction(){
        getSession().getTransaction().rollback();
    }
    
    public int addCourse(String courseName, String courseCRN, String username){
        int result = 0;
        try{
            Course course = new Course();
            course.setCourseName(courseName);
            course.setCourseCRN(courseCRN);
            course.setUsername(username);
            
            beginTransaction();
            getSession().save(course);
            commit();
            result = 1;
        } catch(HibernateException e){
            e.printStackTrace();
            rollbackTransaction();
        } finally{
            close();
        }
        return result;
    }
    
    public List<Course> getCourses(String searchString, String searchType, String username) {
      List<Course> courses = null;
      try {
          beginTransaction();
          Query q= getSession().createQuery("from Course where username= :username and " + searchType + "=:type");
          q.setString("username", username);
          q.setString("type", searchString);
          courses = q.list();
          commit();
      } catch (HibernateException e) {
          e.printStackTrace();
          rollbackTransaction();
      }
      finally {
          close();
      }
      return courses;
  }
}
