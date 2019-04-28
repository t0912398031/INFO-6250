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

import com.neu.edu.exception.ClientException;

import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Client;


@Controller
@RequestMapping("/register/*")
public class RegisterController {

	private static final Log LOGGER = LogFactory.getLog(RegisterController.class);
	
	@Autowired
	ClientDao clientDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView();

		mv.setViewName("register");
		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String register(@ModelAttribute("client") Client client, HttpServletRequest request)
			throws ClientException {
		
		LOGGER.debug(client);
		try {
			Set bitcoins = new HashSet<Bitcoin>();
			for(int i=0;i<10;i++) {
				bitcoins.add(new Bitcoin());
			}

	        client.setBitcoins(bitcoins);
			clientDao.register(client);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}