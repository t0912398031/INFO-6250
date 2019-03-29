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
import com.neu.pojo.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDao extends Dao{
    
    public UserDao() {
    }
    
    public User authenticateLogin(String username, String password) {
        User loggedInUser = null;

        try{      
            Query q= getSession().createQuery("from user where username= :username AND password= :password");
            q.setString("username", username);
            q.setString("password", password);
            loggedInUser = (User)q.uniqueResult();
            commit();
        } catch(HibernateException e){
            e.printStackTrace();
            try {
//                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return loggedInUser;
    }
//    
//    public List<Movie> getMovies(){
//        List<Movie> movies = new ArrayList<Movie>();
//        try{
//            
//            beginTransaction();
////            Query q = getSession().createQuery("from Message where userName= :username");
//            Query q = getSession().createQuery("from Movie");
//            movies = q.list();
//            commit();
//
//        } catch(HibernateException e){
//            e.printStackTrace();
//            try {
//                rollbackTransaction();
//            } catch (Exception ex) {
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            try {
//                //close();
//            } catch (Exception ex) {
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return movies;
//    }
    
    

    
//    public int add(User u){
//        int success = 0;
//        try {
//            beginTransaction();
//            Session session = getSession();
//            session.save(u);
//            commit();
//            success = 1;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            try {
//                rollbackTransaction();
//            } catch (Exception ex) {
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            try {
//                close();
//            } catch (Exception ex) {
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }      
//        return success;
//    }
//  
//    
//    
//    
//    public Movie searchMovie(String id){
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
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            try {
//                //close();
//            } catch (Exception ex) {
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return movies.get(0);
//    }
    
    
    
    
  
    public int add(User u) {
        int success = 0;
        try {
            super.begin();
            getSession().save(u);
            super.commit();
            success = 1;
        } catch (Exception e) {
            super.rollback();
        } finally {
//            super.close();
        }
        return success;
    }

    public User get(String fname) throws Exception {
        try {
            super.begin();
            Query q = getSession().createQuery("from User where fname = :username");
            q.setString("fname", fname);
            User user = (User) q.uniqueResult();
            super.commit();
            return user;
        } catch (Exception e) {
            super.rollback();
            throw new Exception("Could not get user with first name " + fname, e);
        } finally {
//            super.close();
        }
    }

    public void delete(User user) throws Exception {
        try {
            super.begin();
            getSession().delete(user);
            super.commit();
        } catch (Exception e) {
            super.rollback();
            throw new Exception("Could not delete user " + user.getName(), e);
        }
    }
}
