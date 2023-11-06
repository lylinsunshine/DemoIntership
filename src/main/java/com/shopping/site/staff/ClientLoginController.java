package com.shopping.site.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.LoginRequestDTO;
import com.shopping.service.IUserService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/login")
public class ClientLoginController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping
	public ResponseModel<String> login(@RequestBody LoginRequestDTO user) {
		return userService.login(user);
	}
}
