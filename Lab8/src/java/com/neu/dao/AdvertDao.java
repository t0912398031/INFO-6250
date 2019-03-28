package com.neu.dao;

import org.hibernate.HibernateException;

import com.neu.pojo.Advert;
import com.neu.pojo.User;

public class AdvertDao extends DAO {

    public Advert create(Advert advert) throws Exception {
        try {
            super.begin();
            getSession().save(advert);
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            //throw new AdvertException("Could not create advert", e);
            throw new Exception("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Advert advert) throws Exception {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete advert", e);
        }
    }
}