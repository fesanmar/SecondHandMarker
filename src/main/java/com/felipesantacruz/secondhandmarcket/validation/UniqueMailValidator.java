package com.felipesantacruz.secondhandmarcket.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipesantacruz.secondhandmarcket.service.UserService;

@Component
public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String>
{
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		return value != null && !userService.isMailAlreadyInUse(value);
	}

}