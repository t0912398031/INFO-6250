package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.User;

public class UserValidator implements Validator
{
	@Override
	public boolean supports(Class<?> clazz) {
		//This Validator validates *just* User instances
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//basic validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first", "empty.first", "First Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last", "empty.last", "Last Name is Required");
		
		//add additional custom validations below
	}
}
