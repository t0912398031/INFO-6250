package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Order;

public class OrderDao extends DAO {

    public Order create(Order order)
            throws AdvertException {
        try {
            begin();            
            getSession().save(order);     
            commit();
            return order;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating order: " + e.getMessage());
        }
    }

    public void delete(Order order)
            throws AdvertException {
        try {
            begin();
            getSession().delete(order);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete order", e);
        }
    }
    
    public List<Order> list() throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Order");
            List<Order> orders = q.list();
            commit();
            return orders;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not list orders", e);
        }
    	
    }
}