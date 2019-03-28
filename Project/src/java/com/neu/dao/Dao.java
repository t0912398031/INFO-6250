/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;
/**
 *
 * @author Chung-Yang Li
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Dao {
 
    private SessionFactory sessionFactory;
    private static Session session;
    
    protected SessionFactory setUp() throws Exception {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        try {
            this.sessionFactory = configuration.buildSessionFactory(registry);
            
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        
        return sessionFactory;
    }
    
    
    public Session getSession() throws Exception {
        if(session == null) {
            session = setUp().openSession();
        }
        return session;
    }
    
    public void beginTransaction() throws Exception {
        getSession().beginTransaction();
    }

    public void commit() throws Exception {
        getSession().getTransaction().commit();;
    }

    public void close() throws Exception {
        getSession().close();
    }

    public void rollbackTransaction() throws Exception {
        getSession().getTransaction().rollback();
    }
    
}
