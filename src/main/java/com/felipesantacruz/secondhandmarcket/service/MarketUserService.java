package com.felipesantacruz.secondhandmarcket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.repository.UserRepository;

@Service
public class MarketUserService
{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public boolean isMailAlreadyInUse(String email)
	{
		return repository.findByEmail(email) != null;
	}

	public MarketUser register(MarketUser user)
	{
		user.setPassword(encodePasswordForUser(user));
		return repository.save(user);
	}

	private String encodePasswordForUser(MarketUser user)
	{
		return passwordEncoder.encode(user.getPassword());
	}
	
	public MarketUser findById(long id)
	{
		return repository.findById(id).orElse(null);
	}
	
	public MarketUser findByEmail(String email)
	{
		return repository.findByEmail(email);
	}

}
