package com.neu.edu.dao;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.ClientException;
import com.neu.edu.pojo.Client;

public class ClientDao extends DAO {

	public ClientDao() {
	}

	public Client get(long userId) throws ClientException {
		try {
			begin();
			Query q = getSession().createQuery("from Client where userId = :userId");
			q.setLong("userId", userId);
			q.setCacheMode(CacheMode.IGNORE);
			Client user = (Client) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new ClientException("Could not get user " + userId, e);
		}
	}

	public Client register(Client u) throws ClientException {
		try {
			begin();
			getSession().save(u);
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new ClientException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void update(Client u) throws ClientException {
		try {
			begin();
			getSession().update(u);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new ClientException("Could not save the client" + u.getUserId(), e);
		}
	}

	public void delete(Client user) throws ClientException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new ClientException("Could not delete user " + user.getUserName(), e);
		}
	}
	
	public Client authenticateLogin(String username, String password) throws ClientException {
		try {
			begin();
			Query q = getSession().createQuery("from Client where userName= :username AND password= :password");
			q.setString("username", username);
            q.setString("password", password);
			
			Client user = (Client) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new ClientException("Could not get user " + username, e);
		}
	}
	
	 public List<Client> list() throws ClientException{
	    	
    	try {
            begin();
            Query q = getSession().createQuery("from Client");
            List<Client> clients = q.list();
            commit();
            return clients;
        } catch (HibernateException e) {
            rollback();
            throw new ClientException("Could not list users", e);
        }
    	
    }
}
