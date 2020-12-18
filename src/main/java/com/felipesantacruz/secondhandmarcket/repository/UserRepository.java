package com.felipesantacruz.secondhandmarcket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;

public interface UserRepository extends JpaRepository<MarketUser, Long>
{
	MarketUser findByEmail(String email);
}
