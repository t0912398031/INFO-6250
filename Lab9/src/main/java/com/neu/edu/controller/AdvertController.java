//package com.neu.edu.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.neu.edu.dao.AdvertDao;
//import com.neu.edu.dao.CategoryDao;
//import com.neu.edu.dao.UserDao;
//import com.neu.edu.exception.AdvertException;
//import com.neu.edu.exception.CategoryException;
//import com.neu.edu.exception.UserException;
//import com.neu.edu.pojo.Advert;
//import com.neu.edu.pojo.Category;
//import com.neu.edu.pojo.User;
//
//@Controller
//@RequestMapping("/advert/*")
//public class AdvertController {
//
//	private static final Log LOGGER = LogFactory.getLog(AdvertController.class);
//	@Autowired
//	UserDao userdao;
//
//	@Autowired
//	AdvertDao advertdao;
//
//	@Autowired
//	CategoryDao categoryDao;
//
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView createAdvert() throws CategoryException {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDao.list());
//		mv.addObject("advert", new Advert());
//		mv.setViewName("create-advert");
//		return mv;
//	}
//
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public String createAdvert(@ModelAttribute("advert") Advert advert, HttpServletRequest request)
//			throws CategoryException, AdvertException, UserException {
//		int userId = Integer.parseInt(request.getParameter("userId"));
//		LOGGER.debug(advert);
//		try {
//			User user = userdao.get(userId);
//			advert.setUser(user);
//			advertdao.create(advert);
//			for (Category c : advert.getCategories()) {
//				c = categoryDao.get(c.getTitle());
//				c.getAdverts().add(advert);
//				categoryDao.update(c);
//			}
//
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
//		return "redirect:/";
//	}
//}