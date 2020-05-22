package com.shopping.service;

import com.shopping.dto.LoginRequestDTO;
import com.shopping.util.ResponseModel;

public interface IUserService {
	
	ResponseModel<String> login(LoginRequestDTO user);
	
	ResponseModel<Boolean> register();
}
