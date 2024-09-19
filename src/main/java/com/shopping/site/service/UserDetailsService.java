package com.shopping.site.service;

import com.shopping.site.entity.User;
import com.shopping.site.repository.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	//https://www.boraji.com/spring-security-5-custom-userdetailsservice-example

	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userRepository.findByUsername(username);
		UserBuilder builder = null;
		if (user != null) {
			  //user.setPassword("{noop}".concat(user.getPassword()));
		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.password(user.getPassword());
		      builder.roles(user.getRole());
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }
		return builder.build();
	}

	public String login(String username, String password) {
		User user =  userRepository.findByUsernameAndPassword(username, password);
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}
	
}
