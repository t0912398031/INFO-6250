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
import com.neu.pojo.Message;
import com.neu.pojo.Movie;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MovieDao {
//    private BasicDataSource conn;
//
//    public BasicDataSource getConn() {
//        return conn;
//    }
//
//    public void setConn(BasicDataSource conn) {
//        this.conn = conn;
//    }
    
    
    
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;

    private Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sf.openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();;
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }
    
    public List<Movie> getMovies() {
//        Connection connection = null;
//        List<Movie> result = null;
//        try {
//            connection = conn.getConnection();
//            QueryRunner queryRunner = new QueryRunner();
//            ResultSetHandler<List<Movie>> h = new BeanListHandler<Movie>(Movie.class);
//            String query = "SELECT * FROM movies";
//            result = queryRunner.query(connection, query, h);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                DbUtils.close(connection);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            beginTransaction();
//            Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Movie");
//            q.setString("username", user);
            movies = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return movies;

    }
    
    public int addMovie(Movie m) {
        int result = 0;
        try {
            
            beginTransaction();
            getSession().save(m);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
     public int addMovies(String title,String actor,String actress, String genre, int year) {
//        Connection connection = null;
//        int result = 0;
//        try {
//            connection = conn.getConnection();
//            QueryRunner queryRunner = new QueryRunner();
//            String query = "INSERT INTO movies (title, actor, actress, genre, year) VALUES (?,?,?,?,?)";
//            result = queryRunner.update(connection, query,title,actor,actress,genre,year);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            try {
//                DbUtils.close(connection);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;


//        int result = 0;
//        try {
//            Movie movie = new Movie();
//            movie.setActor(actor);
//            movie.setActress(actress);
//            movie.setGenre(genre);
//            movie.setTitle(title);
//            movie.setYear(year);
//            beginTransaction();
//            getSession().save(movie);
//            commit();
//            result = 1;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            rollbackTransaction();
//        } finally {
//            close();
//        }
//        return result;
return 0;
    }
     
     public Movie searchMovie(String id){
        List<Movie> movies = new ArrayList<>();
        try{
            beginTransaction();
            Session session = getSession();
//            Query q = session.createQuery("from Course where username=:username and "+searchType+"=:type");
            Query q = session.createQuery("from Movie where id=:id");
            System.out.println(q.toString());
            q.setString("id", id);

            movies = q.list();
            System.out.println(q.toString());
            commit();
        } catch(HibernateException e){
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return movies.get(0);
    }
}
