package com.neu.dao;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.Category;

public class CategoryDao extends DAO {

    public Category get(String title) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from Category where title = :title");
            q.setString("title", title);
            Category category = (Category) q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }

    public List list() throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not list the categories", e);
        }
    }

    public Category create(Category cat) throws Exception {
        try {
            begin();
            getSession().save(cat);
            commit();
            return null;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating category: " + e.getMessage());
        }
    }

    public void save(Category category) throws Exception {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save the category", e);
        }
    }

    public void delete(Category category) throws Exception {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete the category", e);
        }
    }
}