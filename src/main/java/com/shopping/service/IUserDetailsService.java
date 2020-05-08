package com.shopping.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
	
	String login(String username, String password);

}
