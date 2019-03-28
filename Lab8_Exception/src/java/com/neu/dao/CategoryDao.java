package com.neu.dao;


import com.neu.exception.CategoryException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.Category;

public class CategoryDao extends DAO {

    public Category get(String title) throws CategoryException {
        try {
            super.begin();
            Query q = getSession().createQuery("from Category where title = :title");
            q.setString("title", title);
            Category category = (Category) q.uniqueResult();
            super.commit();
            return category;
        } catch (HibernateException e) {
            super.rollback();
            throw new CategoryException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }

    public List list() throws CategoryException {
        try {
            super.begin();
            Query q = getSession().createQuery("from Category");
            List list = q.list();
            super.commit();
            return list;
        } catch (HibernateException e) {
            super.rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }

    public Category create(Category cat) throws CategoryException {
        try {
            super.begin();
            getSession().save(cat);
            super.commit();
            return null;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Exception while creating category: " + e.getMessage());
        }
    }

    public void save(Category category) throws CategoryException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws CategoryException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not delete the category", e);
        }
    }
}