package com.shopping.site.service2;

import com.shopping.dao.IUserDAO;
import com.shopping.entity.User;
import com.shopping.service.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserDetailsService implements IUserDetailsService {

	//https://www.boraji.com/spring-security-5-custom-userdetailsservice-example

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userDAO.findByUsername(username);		
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

	@Override
	public String login(String username, String password) {
		User user =  userDAO.findByUsernameAndPassword(username, password);
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}
	
}
