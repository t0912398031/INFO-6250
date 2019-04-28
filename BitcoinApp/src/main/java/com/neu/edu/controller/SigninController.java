package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

import com.google.gson.Gson;
import com.neu.edu.dao.BitcoinDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.RecordDao;


import com.neu.edu.exception.BitcoinException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.exception.RecordException;

import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;



@Controller
@RequestMapping("/signin/*")
public class SigninController {

	private static final Log LOGGER = LogFactory.getLog(SigninController.class);
	
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){	
    	return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView signin(HttpServletRequest request) throws ClientException {
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");

		Client loggedUser = clientDao.authenticateLogin(userName, password);

        if (loggedUser == null) {
            request.setAttribute("error", "No user found, please check your username and password");
            return new ModelAndView("home");
        } else if(loggedUser.getUserName().equals("admin")&&loggedUser.getPassword().equals("123")){

        	session.setAttribute("USER", loggedUser);
        	session.setAttribute("admin", "admin");
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
	
	@RequestMapping("/back")
	public String back() {
		return "signin";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return ("redirect:/");
	}
	
	
	@RequestMapping(value = "/bitcoins", method = RequestMethod.POST)
	public ModelAndView bitcoins(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		ModelAndView mv = new ModelAndView();
//		System.out.println(loggeduser.getBitcoins().size());
//		System.out.println(loggeduser.getBitcoins());
		mv.addObject("bitcoins", loggeduser.getBitcoins());
		mv.setViewName("bitcoins");
		return mv;
	}
	
	@RequestMapping(value = "/marketprice", method = RequestMethod.POST)
	public ModelAndView marketprice(HttpServletRequest request) throws OrderException, ClientException, RecordException{

		List<Record> records = recordDao.list();

		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

		for (Record r: records) {	
			map = new HashMap<Object,Object>(); map.put("y", r.getPrice());  map.put("label", r.getDate().getTime()/1000); list.add(map);
		}
//		map = new HashMap<Object,Object>(); map.put("y", 17363);  map.put("label", "2005-06"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 28726);  map.put("label", "2006-07"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 35000);  map.put("label", "2007-08"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 25250);  map.put("label", "2008-09"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 19750);  map.put("label", "2009-10"); list.add(map);

		String dataPoints = gsonObj.toJson(list);
 
		ModelAndView mv = new ModelAndView();
		mv.addObject("dataPoints", dataPoints);
		mv.setViewName("marketprice");
		return mv;

	}
	

}