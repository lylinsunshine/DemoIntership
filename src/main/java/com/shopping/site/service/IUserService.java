package com.shopping.service;

import java.util.List;

import com.shopping.dto.ClientUserInfoDTO;
import com.shopping.dto.LoginRequestDTO;
import com.shopping.dto.RegisterRequestDTO;
import com.shopping.entity.Address;
import com.shopping.entity.User;
import com.shopping.entity.Wishlist;
import com.shopping.util.ResponseModel;

public interface IUserService {
	
	ResponseModel<String> login(LoginRequestDTO user);
	
	ResponseModel<ClientUserInfoDTO> getUserInfo(String username);
	
	ResponseModel<List<Address>> insertOrUpdateAddress(Address address);
	
	ResponseModel<List<Address>> deleteAddress(int id);
	
	ResponseModel<ClientUserInfoDTO> updateUserInfo(User user);

	ResponseModel<String> register(RegisterRequestDTO user);

	ResponseModel<Boolean> isUserNameExist(String name);

	ResponseModel<Wishlist> addWishlist(Wishlist wishlist);

	ResponseModel<List<Wishlist>> getWishlist(String username);

	ResponseModel<Wishlist> deleteWishlist(int wishlistId);
}
