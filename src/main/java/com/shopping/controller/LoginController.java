package com.shopping.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Product;
import com.shopping.service.IUserDetailsService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private IUserDetailsService userService;

	@GetMapping
	public ResponseModel<String> login(@RequestParam String username, @RequestParam String password) {
		//String token = userService.login(username, password);
		String token = userService.login(username, password); 
		//System.out.println(token);
		return new ResponseModel<String>(token, HttpStatus.ACCEPTED, "Token");
	}

}
