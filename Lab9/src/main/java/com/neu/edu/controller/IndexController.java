package com.neu.edu.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.CategoryDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Phone;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/*")
public class IndexController {

//	@Autowired
//	CategoryDao categoryDao;
//
//	@Autowired
//	UserDao userDao;
	
	
	@Autowired
	ClientDao clientDao;
	
	@RequestMapping("/")
	public String viewHome() {
		return "home";
	}
	
	@RequestMapping("/createClient")
	public ModelAndView setupClient() throws CategoryException, ClientException {
//		categoryDao.create("Entertainment");
//		categoryDao.create("Food");
//		categoryDao.create("Sports");
//		categoryDao.create("Medical");

		Client user = new Client();
		user.setUserName("Lipang");
		user.setPassword("123");
		
        Set bitcoins = new HashSet<Bitcoin>();
        bitcoins.add(new Bitcoin());
        bitcoins.add(new Bitcoin());
        user.setBitcoins(bitcoins);
		user = clientDao.register(user);

		return new ModelAndView("home");
	}

//	@RequestMapping("/create")
//	public ModelAndView setup() throws CategoryException, UserException {
//		categoryDao.create("Entertainment");
//		categoryDao.create("Food");
//		categoryDao.create("Sports");
//		categoryDao.create("Medical");
//
//		User user = new User();
//		user.setFirstName("John");
//		user.setLastName("Doe");
//		Set<Phone> phoneNumbers = new HashSet<Phone>();
//		phoneNumbers.add(new Phone("1234567890"));
//		phoneNumbers.add(new Phone("0987654321"));
//		user.setPhoneNumbers(phoneNumbers);
//		user = userDao.register(user);
//
//		user = new User();
//		user.setFirstName("Dohn");
//		user.setLastName("Joe");
//		phoneNumbers = new HashSet<Phone>();
//		phoneNumbers.add(new Phone("246801214"));
//		phoneNumbers.add(new Phone("141208642"));
//		user.setPhoneNumbers(phoneNumbers);
//		user = userDao.register(user);
//		return new ModelAndView("home");
//	}
	
	
//	@RequestMapping("/signin")
//	public ModelAndView signin(HttpServletRequest request) throws CategoryException, ClientException {
//		
//		HttpSession session = request.getSession();
//		String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        
//		Client loggedUser = clientDao.authenticateLogin(userName, password);
//		
//        if (loggedUser == null) {
//            session.setAttribute("USER", "No user found, please check your username and password");
////            System.out.println("no user");
//            return new ModelAndView("home");
//        } else {
//            session.setAttribute("USER", loggedUser);
//            return new ModelAndView("signin");
//            
//
//        }
//	}
}
