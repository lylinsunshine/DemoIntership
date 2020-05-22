package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dto.LoginRequestDTO;
import com.shopping.entity.User;
import com.shopping.repository.UserRepository;
import com.shopping.service.IUserService;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseModel<String> login(LoginRequestDTO user) {
		// TODO Auto-generated method stub
		String token = "";
		User u = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (u!=null) {
			if(u.getRole().equals(user.getRole()))
				token = user.getUsername();
		}
		return new ResponseModel<String>(token, HttpStatus.OK, "Login Response");
	}

	@Override
	public ResponseModel<Boolean> register() {
		// TODO Auto-generated method stub
		return null;
	}

}
