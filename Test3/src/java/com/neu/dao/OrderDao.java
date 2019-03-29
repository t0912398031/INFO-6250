/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.neu.exception.UserException;
import com.neu.pojo.Order;
import com.neu.pojo.Phone;
import com.neu.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Adi
 */
public class OrderDao extends DAO {

    public OrderDao() {
    }

    public int add(Order o) throws UserException {
        int success = 0;
        try {
            super.begin();
            getSession().save(o);
            super.commit();
            success = 1;
        } catch (Exception e) {
            super.rollback();
            throw new UserException("Could not get order " + o.getID(), e);
        }
        return success;
    }

    public User get(String fname) throws UserException {
        try {
            super.begin();
            Query q = getSession().createQuery("from User where fname = :username");
            q.setString("fname", fname);
            User user = (User) q.uniqueResult();
            super.commit();
            return user;
        } catch (Exception e) {
            super.rollback();
            throw new UserException("Could not get user with first name " + fname, e);
        } finally {
//            super.close();
        }
    }

    public void delete(User user) throws UserException {
        try {
            super.begin();
            getSession().delete(user);
            super.commit();
        } catch (Exception e) {
            super.rollback();
            throw new UserException("Could not delete user " + user.getName(), e);
        }
    }
    
    public User authenticateLogin(String username, String password) throws UserException{
        User loggedInUser = null;
        try {
            super.begin();
//            From Class Name
            Query q= getSession().createQuery("from User where userName= :username AND password= :password");
            q.setString("username", username);
            q.setString("password", password);
            loggedInUser = (User)q.uniqueResult();
            super.commit();
            
        } catch (Exception e) {
            super.rollback();
            throw new UserException("Could not get user: " + username, e);
        } finally {
//            super.close();
        }
        return loggedInUser;
    }
}
