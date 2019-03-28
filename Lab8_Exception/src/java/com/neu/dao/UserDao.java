/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.neu.exception.UserException;
import com.neu.pojo.Phone;
import com.neu.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Adi
 */
public class UserDao extends DAO {

    public UserDao() {
    }

    public User add(User u) throws UserException {
        try {
            super.begin();
            getSession().save(u);
            super.commit();
        } catch (Exception e) {
            super.rollback();
            throw new UserException("Could not get user " + u.getFname()+" "+u.getLname(), e);
        }
        return u;
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
            throw new UserException("Could not delete user " + user.getFname(), e);
        }
    }
}
