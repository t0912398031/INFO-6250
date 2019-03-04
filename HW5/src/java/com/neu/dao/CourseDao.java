/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.neu.pojo.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.metamodel.MetadataSources;

/**
 *
 * @author rahulzore
 */
public class CourseDao {
    private SessionFactory sessionFactory;
    private static Session session;
    
    protected SessionFactory setUp() throws Exception {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        try {
            this.sessionFactory = configuration.buildSessionFactory(registry);
            
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        
        return sessionFactory;
    }
    
    public Session getSession() throws Exception {
        if(session == null) {
            session = setUp().openSession();
        }
        return session;
    }
    
    private void beginTransaction() throws Exception {
        getSession().beginTransaction();
    }

    private void commit() throws Exception {
        getSession().getTransaction().commit();;
    }

    private void close() throws Exception {
        getSession().close();
    }

    private void rollbackTransaction() throws Exception {
        getSession().getTransaction().rollback();
    }
    
    public List<Course> getCourses(String searchString, String searchType,String username){
        List<Course> courses = new ArrayList<>();
        try{
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Course where username=:username and "+searchType+"=:type");
            System.out.println(q.toString());
            q.setString("username", username);
            q.setString("type", searchString);
            courses = q.list();
            System.out.println(q.toString());
            commit();
        } catch(HibernateException e){
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
    
    public int addCourse(String coursename, String courseCRN,String username){
        int result = 0;
        try {
            Course course = new Course();
            course.setCourseName(coursename);
            course.setCourseCRN(courseCRN);
            course.setUsername(username);
            beginTransaction();
            Session session = getSession();
            session.save(course);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
}
