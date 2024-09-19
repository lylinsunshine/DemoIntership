package com.shopping.site.staff;

import com.shopping.site.dto.ClientUserInfoDTO;
import com.shopping.site.dto.LoginRequestDTO;
import com.shopping.site.dto.RegisterRequestDTO;
import com.shopping.site.entity.Address;
import com.shopping.site.entity.User;
import com.shopping.site.entity.Wishlist;
import com.shopping.site.service.UserServiceImpl;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clients/user")
@RequiredArgsConstructor
public class ClientUserController {

	private UserServiceImpl userService;
	
	@PostMapping("/get-user-info")
	public Response<ClientUserInfoDTO> getUserInfo(@RequestBody LoginRequestDTO info) {
		return userService.getUserInfo(info.getUsername());
	}
	
	@PostMapping("/checkusername")
	public Response<Boolean> isUserNameExist(@RequestBody LoginRequestDTO user) {
		return userService.isUserNameExist(user.getUsername());
	}
	
	
	@PostMapping("/login")
	public Response<String> login(@RequestBody LoginRequestDTO user) {
		return userService.login(user);
	}
	
	@PostMapping("/register")
	public Response<String> register(@RequestBody RegisterRequestDTO user) {
		return userService.register(user);
	}
	
	@PostMapping("/address")
	public Response<List<Address>> insertOrUpdateAddress(@RequestBody Address address) {
		return userService.insertOrUpdateAddress(address);
	}
	
	@PostMapping("/address/{id}")
	public Response<List<Address>> deleteAddress(@PathVariable int id) {
		return userService.deleteAddress(id);
	}
	
	@PostMapping("/info")
	public Response<ClientUserInfoDTO> updateUserInfo(@RequestBody User user) {
		return userService.updateUserInfo(user);
	}
	
	@PostMapping("/add-wishlist")
	public Response<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
		return userService.addWishlist(wishlist);
	}
	
	@PostMapping("/get-wishlist")
	public Response<List<Wishlist>> getUserWishlist(@RequestBody LoginRequestDTO u) {
		return userService.getWishlist(u.getUsername());
	}
	
	@GetMapping("/delete-wishlist/{wishlistId}")
	public Response<Wishlist> deleteWishlist(@PathVariable int wishlistId) {
		return userService.deleteWishlist(wishlistId);
	}
	
}
