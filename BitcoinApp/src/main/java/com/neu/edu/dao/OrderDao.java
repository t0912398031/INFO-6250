package com.neu.edu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.OrderException;
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
		} finally {
			getSession().clear();
			close();
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
        } finally {
        	getSession().clear();
        	close();
        }
    }

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
    
public List<Order> listByUser(long userId) throws OrderException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Order where userId = :userId");
            q.setLong("userId", userId);
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

	public List<Order> listByTypeAndStatus(String type, String status1, String status2) throws OrderException{
		
		try {
	        begin();
	        Query q = getSession().createQuery("from Order where( status= :status1 or status= :status2) AND type = :type ");
	        q.setString("type", type);
	        q.setString("status1", status1);
	        q.setString("status2", status2);
	        List<Order> orders = q.list();
	        commit();
	        return orders;
	    } catch (HibernateException e) {
	        rollback();
	        throw new OrderException("Could not list orders", e);
	    }
		
	}
}