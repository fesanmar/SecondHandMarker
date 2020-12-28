package com.felipesantacruz.secondhandmarcket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.service.MarketUserService;

@Controller
public class LoginController
{
	@Autowired
	private MarketUserService service;
	
	@GetMapping("/")
	public String welcome()
	{
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login")
	public String login(Model model)
	{
		model.addAttribute("user", new MarketUser());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@Valid @ModelAttribute MarketUser user,
			BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
			return "login";
		service.register(user);
		return "redirect:/auth/login";
	}
}
