package com.felipesantacruz.secondhandmarcket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.model.Product;
import com.felipesantacruz.secondhandmarcket.model.Purchase;
import com.felipesantacruz.secondhandmarcket.repository.ProductRepository;
import com.felipesantacruz.secondhandmarcket.upload.StorageService;

@Service
public class ProductService
{
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private StorageService storageService;
	
	public Product insert(Product p)
	{
		return repository.save(p);
	}
	
	public void delete(long id)
	{
		Product p = findById(id);
		if (p != null)
		{
			deleteImage(p);
			repository.deleteById(id);
		}
	}
	
	public void delete(Product p)
	{
		deleteImage(p);
		repository.delete(p);
	}

	private void deleteImage(Product p)
	{
		if (p.hasImage())
			storageService.delete(p.getImage());
	}
	
	public Product edit(Product p)
	{
		return repository.save(p);
	}
	
	public Product findById(long id)
	{
		return repository.findById(id).orElse(null);
	}
	
	public List<Product> findAll()
	{
		return repository.findAll();
	}
	
	public List<Product> findByOwner(MarketUser owner)
	{
		return repository.findByOwner(owner);
	}
	
	public List<Product> findByPurchase(Purchase p)
	{
		return repository.findByPurchase(p);
	}
	
	public List<Product> findForSale()
	{
		return repository.findByPurchaseIsNull();
	}
	
	public List<Product> findForSaleWithProductName(String name)
	{
		return repository.findByNameContainsIgnoreCaseAndPurchaseIsNull(name);
	}
	
	public List<Product> findByProductNameAndOwner(String name, MarketUser owner)
	{
		return repository.findByNameContainsIgnoreCaseAndOwner(name, owner);
	}
	
	public List<Product> findProductsWithIds(List<Long> ids)
	{
		return repository.findAllById(ids);
	}
}
