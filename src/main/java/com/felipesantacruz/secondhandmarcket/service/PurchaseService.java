package com.felipesantacruz.secondhandmarcket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.model.Product;
import com.felipesantacruz.secondhandmarcket.model.Purchase;
import com.felipesantacruz.secondhandmarcket.repository.PurchaseRepository;

@Service
public class PurchaseService
{
	@Autowired
	private PurchaseRepository repository;
	
	@Autowired
	private ProductService productService;
	
	public Purchase insert(Purchase purchase, MarketUser newOwner)
	{
		purchase.setPurchaser(newOwner);
		return repository.save(purchase);
	}
	
	public Purchase insert(Purchase purchase)
	{
		return repository.save(purchase);
	}
	
	public Product addProductToPurchase(Product product, Purchase purchase)
	{
		product.setPurchase(purchase);
		return productService.edit(product);
	}
	
	public Purchase findById(long id)
	{
		return repository.findById(id).orElse(null);
	}
	
	public List<Purchase> findAll()
	{
		return repository.findAll();
	}
	
	public List<Purchase> findByPurchaser(MarketUser purchase)
	{
		return repository.findByPurchaser(purchase);
	}
}
