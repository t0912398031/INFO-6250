package com.me.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.pojo.User;
import com.me.validator.UserValidator;

@Controller
@RequestMapping("/user.htm")
public class UserFormController {
	@Autowired
	UserValidator uservalidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(uservalidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String formView(ModelMap model, User user) {
		user.setFirst("OLD FIRST"); // if object has values, these would populate the form view automatically
		user.setLast("OLD LAST"); // if an existing object populates the form fields, it is called
									// form-backing-object

		model.addAttribute("user", user);
		return "user-form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String successView(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "user-form";  //the are validation errors, go to the form view
		}
		//no errors, so go to the success view
		return "user-success";
	}

}
