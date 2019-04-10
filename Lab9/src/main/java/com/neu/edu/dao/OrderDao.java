package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;

public class OrderDao extends DAO {

    public Order create(Order order)
            throws OrderException {
        try {
            begin();            
            getSession().save(order);     
            commit();
            return order;
        } catch (HibernateException e) {
            rollback();
            throw new OrderException("Exception while creating order: " + e.getMessage());
        }
    }
    
    public Order get(long orderId) throws OrderException {
		try {
			begin();
			Query q = getSession().createQuery("from Order where orderId = :orderId");
			q.setLong("orderId", orderId);
			Order o = (Order) q.uniqueResult();
			commit();
			return o;
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Could not get order " + orderId, e);
		}
	}
    
    public void update(Order u) throws OrderException {
		try {
			begin();
			getSession().update(u);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Could not save the order" + u.getOrderId(), e);
		}
	}

    public void delete(Order order) throws OrderException {
        try {
            begin();
            getSession().delete(order);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new OrderException("Could not delete order", e);
        }
    }
//    
//    public int deleteById(long Id) throws OrderException {
//    	int success = 0;
//		try {
//			begin();
//			Query q = getSession().createQuery("delete from Order where orderId = :Id");
//			q.setLong("orderId", Id);
//			commit();
//			success = 1;
//		} catch (HibernateException e) {
//			rollback();
//			throw new OrderException("Could not delete order " + Id, e);
//		}
//		return success;
//	}
//    
    public List<Order> list() throws OrderException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Order");
            List<Order> orders = q.list();
            commit();
            return orders;
        } catch (HibernateException e) {
            rollback();
            throw new OrderException("Could not list orders", e);
        }
    	
    }
    
    public List<Order> listByType(String type) throws OrderException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Order where type = :type");
            q.setString("type", type);
            List<Order> orders = q.list();
            commit();
            return orders;
        } catch (HibernateException e) {
            rollback();
            throw new OrderException("Could not list orders", e);
        }
    	
    }

	public List<Order> listByTypeAndStatus(String type, String status) throws OrderException{
		
		try {
	        begin();
	        Query q = getSession().createQuery("from Order where type = :type AND status= :status");
	        q.setString("type", type);
	        q.setString("status", status);
	        List<Order> orders = q.list();
	        commit();
	        return orders;
	    } catch (HibernateException e) {
	        rollback();
	        throw new OrderException("Could not list orders", e);
	    }
		
	}
}