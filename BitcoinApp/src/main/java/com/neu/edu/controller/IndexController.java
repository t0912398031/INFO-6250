package com.neu.edu.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.ClientDao;
import com.neu.edu.exception.ClientException;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Client;

@Controller
@RequestMapping("/*")
public class IndexController {

	@Autowired
	ClientDao clientDao;
	
	@RequestMapping("/")
	public String viewHome() {
		return "home";
	}
	
	@RequestMapping("/createClient")
	public ModelAndView setupClient() throws ClientException {

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
}
