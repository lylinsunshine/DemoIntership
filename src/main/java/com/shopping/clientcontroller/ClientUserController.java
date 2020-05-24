package com.shopping.clientcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.ClientUserInfoDTO;
import com.shopping.dto.LoginRequestDTO;
import com.shopping.entity.Address;
import com.shopping.entity.User;
import com.shopping.service.IUserService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/user")
public class ClientUserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/{username}")
	public ResponseModel<ClientUserInfoDTO> getUserInfo(@PathVariable String username) {
		return userService.getUserInfo(username);
	}
	
	
	@PostMapping("/login")
	public ResponseModel<String> login(@RequestBody LoginRequestDTO user) {
		return userService.login(user);
	}
	
	@PostMapping("/address")
	public ResponseModel<List<Address>> insertOrUpdateAddress(@RequestBody Address address) {
		return userService.insertOrUpdateAddress(address);
	}
	
	@PostMapping("/address/{id}")
	public ResponseModel<List<Address>> deleteAddress(@PathVariable int id) {
		return userService.deleteAddress(id);
	}
	
	@PostMapping("/info")
	public ResponseModel<ClientUserInfoDTO> updateUserInfo(@RequestBody User user) {
		return userService.updateUserInfo(user);
	}
	
}
