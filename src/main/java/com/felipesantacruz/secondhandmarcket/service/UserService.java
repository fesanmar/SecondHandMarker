package com.felipesantacruz.secondhandmarcket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesantacruz.secondhandmarcket.model.User;
import com.felipesantacruz.secondhandmarcket.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepo;
	
	public boolean isMailAlreadyInUse(String email)
	{
		return userRepo.findByEmail(email) != null;
	}

	public User resgiter(User user)
	{
		return userRepo.save(user);
	}

}
