package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.BitcoinException;

import com.neu.edu.pojo.Bitcoin;

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
			throw new BitcoinException("Could not get bitcoin " + USER_ID, e);
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
    

}