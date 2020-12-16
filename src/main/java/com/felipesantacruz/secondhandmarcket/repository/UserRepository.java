package com.felipesantacruz.secondhandmarcket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.secondhandmarcket.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail(String email);
}
