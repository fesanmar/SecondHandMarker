package com.felipesantacruz.secondhandmarcket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.model.Product;
import com.felipesantacruz.secondhandmarcket.model.Purchase;
import com.felipesantacruz.secondhandmarcket.service.MarketUserService;
import com.felipesantacruz.secondhandmarcket.service.ProductService;
import com.felipesantacruz.secondhandmarcket.service.PurchaseService;

@Controller
@RequestMapping("/app")
public class PurchaseController
{
	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService productService;

	@Autowired
	private MarketUserService userService;

	@Autowired
	private HttpSession session;

	private MarketUser user;

	@ModelAttribute("carrito")
	public List<Product> productsInShoppingCart()
	{
		List<Long> shoppingCartContent = getShoppingCartIdsFromSession();
		return productService.findProductsWithIds(shoppingCartContent);
	}

	@ModelAttribute("total_carrito")
	public Double totalShoppingCart()
	{
		List<Product> productsInShoppingCart = productsInShoppingCart();
		return totalAmountFromProductsList(productsInShoppingCart);
	}

	private double totalAmountFromProductsList(List<Product> productsInShoppingCart)
	{
		return productsInShoppingCart.stream()
				.mapToDouble(p -> p.getPrice())
				.sum();
	}

	@ModelAttribute("mis_compras")
	public List<Purchase> getMyPurchases()
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return purchaseService.findByPurchaser(user);
	}

	@GetMapping("/carrito")
	public String showShoppingCart(Model model)
	{
		return "app/purchase/shopping_cart.html";
	}

	@GetMapping("/carrito/add/{id}")
	public String addToShoppingCart(Model model, @PathVariable Long id)
	{
		List<Long> shoppingCartContent = getShoppingCartIdsFromSession();
		if (!shoppingCartContent.contains(id))
			shoppingCartContent.add(id);
		session.setAttribute("carrito", shoppingCartContent);
		return "redirect:/app/carrito";
	}
	
	@GetMapping("/carrito/eliminar/{id}")
	public String removeFromShoppingCart(Model model, @PathVariable Long id)
	{
		List<Long> shoppingCartContent = getShoppingCartIdsFromSession();
		shoppingCartContent.remove(id);
		if (shoppingCartContent.isEmpty())
			session.removeAttribute("carrito");
		else
			session.setAttribute("carrito", shoppingCartContent);
		return "redirect:/app/carrito";
	}

	@SuppressWarnings("unchecked")
	private List<Long> getShoppingCartIdsFromSession()
	{
		
		List<Long> shoppingCartContent = (List<Long>) session.getAttribute("carrito");
		if (shoppingCartContent == null)
			return new ArrayList<>();
		return shoppingCartContent;
	}
	
	@GetMapping("/carrito/finalizar")
	public String checkout()
	{
		if (getShoppingCartIdsFromSession().isEmpty())
			return "redirect:/public";
		List<Product> productToCheck = productsInShoppingCart();
		
		Purchase purchase = purchaseService.insert(new Purchase(), user);
		
		productToCheck.forEach(p -> purchaseService.addProductToPurchase(p, purchase));
		
		session.removeAttribute("carrito");
		
		return "redirect:/app/compra/factura/" + purchase.getId();
	}
	
	@GetMapping("/compra/factura/{id}")
	public String invoice(Model model, @PathVariable Long id)
	{
		Purchase purchaseToInvoice = purchaseService.findById(id);
		List<Product> products = productService.findByPurchase(purchaseToInvoice);
		
		model.addAttribute("compra", purchaseToInvoice);
		model.addAttribute("productos", products);
		model.addAttribute("total_compra", totalAmountFromProductsList(products));
		
		return "/app/purchase/invoice";
	}
	
	@GetMapping("/miscompras")
	public String myPurchases(Model model)
	{
		return "/app/purchase/my_purchases";
	}

}
