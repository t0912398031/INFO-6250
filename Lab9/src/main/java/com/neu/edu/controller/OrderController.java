package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
import com.neu.edu.dao.BitcoinDao;
import com.neu.edu.dao.CategoryDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.RecordDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.BitcoinException;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;
import com.neu.edu.pojo.User;
import com.neu.service.Service;

@Controller
@RequestMapping("/signin/order/*")
public class OrderController {

	private static final Log LOGGER = LogFactory.getLog(OrderController.class);
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	BitcoinDao bitcoinDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	RecordDao recordDao;
	
	Comparator<Order> orderDateComparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
			return o1.getDate().compareTo(o2.getDate());       	
        }
    };

//	@RequestMapping("/")
//	public String viewHome() {
//		return "home";
//	}
	
	@RequestMapping("/back")
	public String back() {
		return "signin";
	}
	
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
        } else if(loggedUser.getUserName().equals("admin")&&loggedUser.getPassword().equals("123")){

        	session.setAttribute("USER", loggedUser);
            session.setAttribute("error", "");
            
            List<Client> clients = clientDao.list();

            ModelAndView mv = new ModelAndView();
    		mv.addObject("clients", clients);
    		mv.setViewName("admin");
            
            return mv;
        } else {
            session.setAttribute("USER", loggedUser);
            session.setAttribute("error", "");
            return new ModelAndView("signin");
        }
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) throws OrderException, ClientException{

		Order order = orderDao.get(Long.parseLong(request.getParameter("delete")));
		orderDao.delete(order);
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		Client u = clientDao.get(loggeduser.getUserId());
		Set orders = u.getOrders();
		List<Order> o = new ArrayList<Order>(orders);
		
        Collections.sort(o, orderDateComparator);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders", orders);
		mv.setViewName("orderlist");
		return mv;
	}
	
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	public ModelAndView record(HttpServletRequest request) throws OrderException, ClientException{
//		HttpSession session = request.getSession();
//		session.invalidate();
		Order order = orderDao.get(Long.parseLong(request.getParameter("record")));
		
		List<Record> records = recordDao.listByOrderId(order.getOrderId());
		
		for (Record r: records) {System.out.println(r.getAmount());}
		
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		Client u = clientDao.get(loggeduser.getUserId());
		Set orders = u.getOrders();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("records", records);
		mv.setViewName("recordlist");
		return mv;

	}

//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView register() throws CategoryException {
//		ModelAndView mv = new ModelAndView();
////		mv.addObject("categories", categoryDao.list());
////		mv.addObject("advert", new Advert());
//		mv.setViewName("register");
//		return mv;
//	}

//	@RequestMapping(value = "/order", method = RequestMethod.POST)
//	public ModelAndView order(@ModelAttribute("order") Order order, HttpServletRequest request)
//			throws CategoryException, AdvertException, ClientException {
//		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
////		System.out.println(loggeduser);
////		System.out.println(loggeduser.getOrders());
//		order.setStatus("Pending");
//		LOGGER.debug(order);
//		try {
//			Set orders;
//			Client u = clientDao.get(loggeduser.getUserId());
//			if(u.getOrders().size() == 0) {
//				orders = new HashSet<Order>();
//			} else {
//				orders = u.getOrders();
//			}
//			orders.add(order);
//			u.setOrders(orders);
//			
////			for(Order o : loggeduser.getOrders()) {System.out.println(o.getPrice());}
////			System.out.println(loggeduser.getOrders());
//			clientDao.update(u);
//			
//			Client u2 = clientDao.get(loggeduser.getUserId());
//			orders = u2.getOrders();
//			
//			ModelAndView mv = new ModelAndView();
//			mv.addObject("orders", orders);
////			mv.addObject("advert", new Advert());
//			mv.setViewName("orderlist");
//			return mv;
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView("signin");
//	}
	
//	@RequestMapping(value = "/view", method = RequestMethod.POST)
//	public ModelAndView view(@ModelAttribute("order") Order order, HttpServletRequest request)
//			throws CategoryException, AdvertException, ClientException {
//		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
//		Client u = clientDao.get(loggeduser.getUserId());
//		Set orders = u.getOrders();
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("orders", orders);
//		mv.setViewName("orderlist");
//		return mv;
//	}
	
}