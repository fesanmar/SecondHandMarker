package com.felipesantacruz.secondhandmarcket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.secondhandmarcket.model.Purchase;
import com.felipesantacruz.secondhandmarcket.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>
{
	List<Purchase> findByPurchaser(User u);
}
