package com.neu.edu.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.CategoryDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Phone;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/*")
public class IndexController {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	UserDao userDao;
	
	@RequestMapping("/")
	public String viewHome() {
		return "home";
	}

	@RequestMapping("/create")
	public ModelAndView setup() throws CategoryException, UserException {
		categoryDao.create("Entertainment");
		categoryDao.create("Food");
		categoryDao.create("Sports");
		categoryDao.create("Medical");

		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		Set<Phone> phoneNumbers = new HashSet<Phone>();
		phoneNumbers.add(new Phone("1234567890"));
		phoneNumbers.add(new Phone("0987654321"));
		user.setPhoneNumbers(phoneNumbers);
		user = userDao.register(user);

		user = new User();
		user.setFirstName("Dohn");
		user.setLastName("Joe");
		phoneNumbers = new HashSet<Phone>();
		phoneNumbers.add(new Phone("246801214"));
		phoneNumbers.add(new Phone("141208642"));
		user.setPhoneNumbers(phoneNumbers);
		user = userDao.register(user);
		return new ModelAndView("home");
	}
}
