package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.BitcoinException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.User;

public class BitcoinDao extends DAO {

	public Bitcoin get(Long USER_ID) throws BitcoinException {
		try {
			begin();
			Query q = getSession().createQuery("from Bitcoin where USER_ID = :USER_ID");
			q.setLong("USER_ID", USER_ID);
			List<Bitcoin> bitcoins = q.list();		
			commit();
			if (bitcoins.size()>0) {
			  return bitcoins.get(0);
			}
			return null;
		} catch (HibernateException e) {
			rollback();
			throw new BitcoinException("Could not get user " + USER_ID, e);
		}
	}
	
    public Advert create(Advert advert)
            throws AdvertException {
        try {
            begin();            
            getSession().save(advert);     
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Bitcoin b)
            throws BitcoinException {
        try {
            begin();
            getSession().delete(b);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new BitcoinException("Could not delete bitcoin", e);
        }
    }
    
    public List<Advert> list() throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Advert");
            List<Advert> adverts = q.list();
            commit();
            return adverts;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    	
    }
}