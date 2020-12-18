package com.felipesantacruz.secondhandmarcket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.secondhandmarcket.model.Purchase;
import com.felipesantacruz.secondhandmarcket.model.MarketUser;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>
{
	List<Purchase> findByPurchaser(MarketUser u);
}
