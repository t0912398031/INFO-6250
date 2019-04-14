package com.neu.edu.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.neu.edu.dao.ClientDao;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/register/*")
public class RegisterController {

	private static final Log LOGGER = LogFactory.getLog(RegisterController.class);
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

//	@RequestMapping("/")
//	public String viewHome() {
//		return "home";
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView register() throws CategoryException {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDao.list());
//		mv.addObject("advert", new Advert());
		mv.setViewName("register");
		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String register(@ModelAttribute("client") Client client, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException {
		
		LOGGER.debug(client);
		try {
			Set bitcoins = new HashSet<Bitcoin>();
			for(int i=0;i<10;i++) {
				bitcoins.add(new Bitcoin());
			}
//	        bitcoins.add(new Bitcoin());
//	        bitcoins.add(new Bitcoin());
//	        bitcoins.add(new Bitcoin());
//	        bitcoins.add(new Bitcoin());
//	        bitcoins.add(new Bitcoin());

	        client.setBitcoins(bitcoins);
			clientDao.register(client);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}