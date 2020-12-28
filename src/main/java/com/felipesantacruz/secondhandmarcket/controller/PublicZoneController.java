package com.felipesantacruz.secondhandmarcket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.felipesantacruz.secondhandmarcket.model.Product;
import com.felipesantacruz.secondhandmarcket.service.ProductService;

@Controller
@RequestMapping("/public") // Applies to every method
public class PublicZoneController
{
	@Autowired
	private ProductService service;
	
	@ModelAttribute("products")
	public List<Product> productsForSale()
	{
		return service.findForSale();
	}
	
	@GetMapping({ "/", "/index" })
	public String index(Model model, @RequestParam(name = "q", required = false) String query)
	{
		if (query != null)
			model.addAttribute("products", service.findForSaleWithProductName(query));
		return "index";
	}
	
	@GetMapping("/producto/{id}")
	public String showProduct(Model model, @PathVariable Long id)
	{
		Product product = service.findById(id);
		if (product != null)
		{
			model.addAttribute("product", product);
			return "product";
		}
		return "redirect:/public";
	}
}
