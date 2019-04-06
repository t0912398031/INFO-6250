package com.neu.edu.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.AdvertDao;
import com.neu.edu.dao.CategoryDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/signin/*")
public class SigninController {

	private static final Log LOGGER = LogFactory.getLog(SigninController.class);
//	@Autowired
//	UserDao userdao;
//
//	@Autowired
//	AdvertDao advertdao;
//
//	@Autowired
//	CategoryDao categoryDao;
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	OrderDao orderDao;

//	@RequestMapping("/")
//	public String viewHome() {
//		return "home";
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView signin(HttpServletRequest request) throws CategoryException, ClientException {
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        
		Client loggedUser = clientDao.authenticateLogin(userName, password);
		
        if (loggedUser == null) {
            session.setAttribute("error", "No user found, please check your username and password");
            System.out.println("no user");
            return new ModelAndView("home");
        } else {
            session.setAttribute("USER", loggedUser);
            session.setAttribute("error", "");
            return new ModelAndView("signin");
            

        }
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return ("redirect:/");
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register() throws CategoryException {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDao.list());
//		mv.addObject("advert", new Advert());
		mv.setViewName("register");
		return mv;
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ModelAndView order(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException {
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		System.out.println(loggeduser);
		System.out.println(loggeduser.getOrders());
		LOGGER.debug(order);
		try {
			Set orders;
			Client u = clientDao.get(loggeduser.getUserId());
			if(u.getOrders().size() == 0) {
				orders = new HashSet<Order>();
			} else {
				orders = u.getOrders();
			}
			orders.add(order);
			u.setOrders(orders);
			
//			for(Order o : loggeduser.getOrders()) {System.out.println(o.getPrice());}
//			System.out.println(loggeduser.getOrders());
			clientDao.update(u);
			
			
			
			ModelAndView mv = new ModelAndView();
//			mv.addObject("categories", categoryDao.list());
//			mv.addObject("advert", new Advert());
			mv.setViewName("orderlist");
			return mv;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ModelAndView("signin");
	}
}