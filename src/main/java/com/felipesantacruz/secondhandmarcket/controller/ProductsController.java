package com.felipesantacruz.secondhandmarcket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.model.Product;
import com.felipesantacruz.secondhandmarcket.service.MarketUserService;
import com.felipesantacruz.secondhandmarcket.service.ProductService;
import com.felipesantacruz.secondhandmarcket.upload.StorageService;

@Controller
@RequestMapping("/app")
public class ProductsController
{
	
	@Autowired
	private ProductService productService;

	@Autowired
	private MarketUserService userService;
	
	@Autowired
	private StorageService storageService;

	private MarketUser user;
	
	@ModelAttribute("misproductos")
	public List<Product> getMyProducts()
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return productService.findByOwner(user);
	}
	
	@GetMapping("/misproductos")
	public String myProducts(Model model, @RequestParam(name = "q", required = false) String query)
	{
		if (query != null)
			model.addAttribute("misproductos", productService.findForSaleWithProductName(query));
		return "app/product/my_products";
	}
	
	@GetMapping("/misproductos/{id}/eliminar")
	public String removeProduct(Model model, @PathVariable Long id)
	{
		Product product = productService.findById(id);
		if (product != null && product.getPurchase() == null)
			productService.delete(product);
		return "redirect:/app/misproductos";
	}
	
	@GetMapping("/producto/nuevo")
	public String newProduct(Model model)
	{
		model.addAttribute("producto", new Product());
		System.out.println(new Product().getId());
		return "app/product/form";
	}
	
	@PostMapping("/producto/nuevo/submit")
	public String newProductSubmit(@ModelAttribute Product product, 
			@RequestParam(name = "file") MultipartFile file)
	{
		if (!file.isEmpty())
		{
			String image = storageService.store(file);
			product.setImage(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", image)
					.build()
					.toUriString());
		}
		product.setOwner(user);
		productService.insert(product);
		return "redirect:/app/misproductos";
	}

}
