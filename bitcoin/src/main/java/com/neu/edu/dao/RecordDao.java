package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.exception.RecordException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;

public class RecordDao extends DAO {

    public Record create(Record record)
            throws RecordException {
        try {
            begin();            
            getSession().save(record);     
            commit();
            return record;
        } catch (HibernateException e) {
            rollback();
            throw new RecordException("Exception while creating record: " + e.getMessage());
        }
    }

    public void delete(Record record)
            throws RecordException {
        try {
            begin();
            getSession().delete(record);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new RecordException("Could not delete record", e);
        }
    }
    
    public List<Record> list() throws RecordException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Record");
            List<Record> records = q.list();
            commit();
            return records;
        } catch (HibernateException e) {
            rollback();
            throw new RecordException("Could not list records", e);
        }
    	
    }
    
    public List<Record> listByOrderId(long orderId) throws RecordException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Record where orderId = :orderId");
            q.setLong("orderId", orderId);
            List<Record> orders = q.list();
            commit();
            return orders;
        } catch (HibernateException e) {
            rollback();
            throw new RecordException("Could not list records", e);
        }
    	
    }
}