package com.felipesantacruz.secondhandmarcket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.felipesantacruz.secondhandmarcket.model.MarketUser;
import com.felipesantacruz.secondhandmarcket.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		MarketUser user = userRepository.findByEmail(username);
		UserBuilder builder = null;
		
		if (user != null)
		{
			builder = User.withUsername(username)
				.disabled(false)
				.password(user.getPassword())
				.authorities(new SimpleGrantedAuthority("ROLE_USER"));
				
		} else
		{
			throw new UsernameNotFoundException("User " + "<"+ username +">" + "couldn't be found");
		}
		
		return builder.build();
	}

}
