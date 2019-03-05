/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;
/**
 *
 * @author Chung-Yang Li
 */
import com.neu.pojo.Message;
import com.neu.pojo.Movie;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MovieDao {

//    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//    private Session session = null;
//
//    private Session getSession() {
//        if (session == null || !session.isOpen()) {
//            session = sf.openSession();
//        }
//        return session;
//    }
//
//    private void beginTransaction() {
//        getSession().beginTransaction();
//    }
//
//    private void commit() {
//        getSession().getTransaction().commit();;
//    }
//
//    private void close() {
//        getSession().close();
//    }
//
//    private void rollbackTransaction() {
//        getSession().getTransaction().rollback();
//    }
    
//    public List<Movie> getMovies() {
//    
//        List<Movie> movies = new ArrayList<Movie>();
//        try {
//            beginTransaction();
////            Query q = getSession().createQuery("from Message where userName= :username");
//            Query q = getSession().createQuery("from Movie");
////            q.setString("username", user);
//            movies = q.list();
//            commit();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            rollbackTransaction();
//        } finally {
//            close();
//        }
//        return movies;
//
//    }
    
//    public int addMovie(Movie m) {
//        int result = 0;
//        try {
//            
//            beginTransaction();
//            getSession().save(m);
//            commit();
//            result = 1;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            rollbackTransaction();
//        } finally {
//            close();
//        }
//        return result;
//    }
    
     
//     public Movie searchMovie(String id){
//        List<Movie> movies = new ArrayList<>();
//        try{
//            beginTransaction();
//            Session session = getSession();
////            Query q = session.createQuery("from Course where username=:username and "+searchType+"=:type");
//            Query q = session.createQuery("from Movie where id=:id");
//            System.out.println(q.toString());
//            q.setString("id", id);
//
//            movies = q.list();
//            System.out.println(q.toString());
//            commit();
//        } catch(HibernateException e){
//            e.printStackTrace();
//            try {
//                rollbackTransaction();
//            } catch (Exception ex) {
//                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            try {
//                //close();
//            } catch (Exception ex) {
//                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return movies.get(0);
//    }
     
     
     
     
     
     
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
    public List<Movie> getMovies(){
        List<Movie> movies = new ArrayList<Movie>();
        try{
            
            beginTransaction();
//            Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Movie");
            movies = q.list();
            commit();

        } catch(HibernateException e){
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return movies;
    }
    
    public int addMovie(Movie m){
        int result = 0;
        try {
            beginTransaction();
            Session session = getSession();
            session.save(m);
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
    
    public Movie searchMovie(String id){
        List<Movie> movies = new ArrayList<>();
        try{
            beginTransaction();
            Session session = getSession();
//            Query q = session.createQuery("from Course where username=:username and "+searchType+"=:type");
            Query q = session.createQuery("from Movie where id=:id");
            System.out.println(q.toString());
            q.setString("id", id);

            movies = q.list();
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
        return movies.get(0);
    }
}
