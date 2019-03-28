/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.AdvertDao;
import com.neu.dao.CategoryDao;
import com.neu.dao.UserDao;
import com.neu.pojo.Advert;
import com.neu.pojo.Category;
import com.neu.pojo.Phone;
import com.neu.pojo.User;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Adi
 */
public class UserController extends AbstractController {

    UserDao userDao;
    AdvertDao advertDao;
    CategoryDao categoryDao;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {

        // Userdao, phone, user
        userDao = (UserDao) getApplicationContext().getBean("userdao");
        advertDao = (AdvertDao) getApplicationContext().getBean("advertdao");
        User u = (User) getApplicationContext().getBean("user");

        Phone ph1 = new Phone("1231231");
        ph1.setUser(u);
        Phone ph2 = new Phone("98989");
        ph2.setUser(u);

        Set set1 = new HashSet<>();
        set1.add(ph1);
        set1.add(ph2);

        u.setFname("John");
        u.setLname("Doe");
        u.setPhNum(set1);

        userDao.add(u);
        Advert ad = new Advert("Advert title1", "Advert message ", u);
        advertDao.create(ad);

        Set set2 = new HashSet<>();
        set2.add(ad);
        Category cat = new Category("Category Title ", set2);
        categoryDao = (CategoryDao) getApplicationContext().getBean("categorydao");
        categoryDao.create(cat);
        return new ModelAndView("user");
    }

}
