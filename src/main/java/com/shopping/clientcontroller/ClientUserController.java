package com.shopping.clientcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.ClientUserInfoDTO;
import com.shopping.dto.LoginRequestDTO;
import com.shopping.dto.RegisterRequestDTO;
import com.shopping.entity.Address;
import com.shopping.entity.User;
import com.shopping.entity.Wishlist;
import com.shopping.service.IUserService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/user")
public class ClientUserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/get-user-info")
	public ResponseModel<ClientUserInfoDTO> getUserInfo(@RequestBody LoginRequestDTO info) {
		return userService.getUserInfo(info.getUsername());
	}
	
	@PostMapping("/checkusername")
	public ResponseModel<Boolean> isUserNameExist(@RequestBody LoginRequestDTO user) {
		return userService.isUserNameExist(user.getUsername());
	}
	
	
	@PostMapping("/login")
	public ResponseModel<String> login(@RequestBody LoginRequestDTO user) {
		return userService.login(user);
	}
	
	@PostMapping("/register")
	public ResponseModel<String> register(@RequestBody RegisterRequestDTO user) {
		return userService.register(user);
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
	
	@PostMapping("/add-wishlist")
	public ResponseModel<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
		return userService.addWishlist(wishlist);
	}
	
	@PostMapping("/get-wishlist")
	public ResponseModel<List<Wishlist>> getUserWishlist(@RequestBody LoginRequestDTO u) {
		return userService.getWishlist(u.getUsername());
	}
	
	@GetMapping("/delete-wishlist/{wishlistId}")
	public ResponseModel<Wishlist> deleteWishlist(@PathVariable int wishlistId) {
		return userService.deleteWishlist(wishlistId);
	}
	
}
