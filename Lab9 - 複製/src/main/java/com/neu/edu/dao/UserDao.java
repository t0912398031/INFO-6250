package com.neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.User;

public class UserDao extends DAO {

	public UserDao() {
	}

	public User get(long userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userId = :userId");
			q.setLong("userId", userId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}

	public User register(User u) throws UserException {
		try {
			begin();
			getSession().save(u);
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getFirstName() + "  " + user.getLastName(), e);
		}
	}
}
