package com.neu.dao;

import com.neu.exception.AdvertException;
import org.hibernate.HibernateException;

import com.neu.pojo.Advert;
import com.neu.pojo.User;

public class AdvertDao extends DAO {

    public Advert create(Advert advert) throws AdvertException {
        try {
            super.begin();
            getSession().save(advert);
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating advert: "+ e.getMessage());
        }
    }

    public void delete(Advert advert) throws AdvertException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    }
}