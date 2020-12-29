package com.felipesantacruz.secondhandmarcket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.service.MarketUserService;
import com.felipesantacruz.secondhandmarcket.upload.StorageService;

@Controller
public class LoginController
{
	@Autowired
	private MarketUserService service;
	
	@Autowired
	private StorageService storageService;
	
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
	public String register(@ModelAttribute MarketUser user,
			@RequestParam(name = "file") MultipartFile file)
	{
		String image = storageService.store(file);
		user.setAvatar(MvcUriComponentsBuilder
				.fromMethodName(FilesController.class, "serveFile", image)
				.build()
				.toUriString());
		service.register(user);
		return "redirect:/auth/login";
	}
}
