package com.felipesantacruz.secondhandmarcket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.secondhandmarcket.model.Product;
import com.felipesantacruz.secondhandmarcket.model.Purchase;
import com.felipesantacruz.secondhandmarcket.model.MarketUser;

public interface ProductRepository extends JpaRepository<Product, Long>
{
	List<Product> findByOwner(MarketUser owner);
	
	List<Product> findByPurchase(Purchase purchase);
	
	/*
	 * Find all product where the purchase is <code>null</code>. 
	 * It means, the products that are for sale.
	 */
	List<Product> findByPurchaseIsNull();
	
	/*
	 * Find all product where the purchase is <code>null</code> and name contains <code>name</code>. 
	 * It means, the products that are for sale and contains the referred string.
	 */
	List<Product> findByNameContainsIgnoreCaseAndPurchaseIsNull(String name);
	
	
	List<Product> findByNameContainsIgnoreCaseAndOwner(String name, MarketUser owner);
	
}
