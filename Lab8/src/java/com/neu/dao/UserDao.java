/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

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

    public User add(User u) {
        try {
            super.begin();
            getSession().save(u);
            super.commit();
        } catch (Exception e) {
            super.rollback();
        } finally {
//            super.close();
        }
        return u;
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
            throw new Exception("Could not delete user " + user.getFname(), e);
        }
    }
}
