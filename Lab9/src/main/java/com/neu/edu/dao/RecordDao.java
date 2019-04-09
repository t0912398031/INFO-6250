package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;

public class RecordDao extends DAO {

    public Record create(Record record)
            throws AdvertException {
        try {
            begin();            
            getSession().save(record);     
            commit();
            return record;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating record: " + e.getMessage());
        }
    }

    public void delete(Record record)
            throws AdvertException {
        try {
            begin();
            getSession().delete(record);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete record", e);
        }
    }
    
    public List<Record> list() throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Record");
            List<Record> records = q.list();
            commit();
            return records;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not list records", e);
        }
    	
    }
}