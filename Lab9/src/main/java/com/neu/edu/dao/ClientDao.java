package com.neu.edu.dao;

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
}
