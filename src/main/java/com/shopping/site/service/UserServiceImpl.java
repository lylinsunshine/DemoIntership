package com.shopping.site.service;

import com.shopping.site.dto.ClientUserInfoDTO;
import com.shopping.site.dto.LoginRequestDTO;
import com.shopping.site.dto.RegisterRequestDTO;
import com.shopping.site.entity.Address;
import com.shopping.site.entity.User;
import com.shopping.site.entity.Wishlist;
import com.shopping.site.repository.AddressRepository;
import com.shopping.site.repository.UserRepository;
import com.shopping.site.repository.WishlistRepository;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {

	private final UserRepository userRepository;

	private final AddressRepository addressRepository;

	private final WishlistRepository wishlistRepository;

	public Response<String> login(LoginRequestDTO user) {
		String token = "";
		User u = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (u!=null) {
			if(u.getRole().equals(user.getRole()))
				token = user.getUsername();
		}
		return new Response<String>(token, 200, "Login Response");
	}

	public Response<ClientUserInfoDTO> getUserInfo(String username) {
		ModelMapper modelMapper = new ModelMapper();
		User u = userRepository.findByUsername(username);
		if(u!=null) {
			ClientUserInfoDTO dto = modelMapper.map(u, ClientUserInfoDTO.class);
			return new Response<ClientUserInfoDTO>(dto, 200, "Get Success");
		} else {
			return new Response<ClientUserInfoDTO>(null, 200, "Get Fail");
		}
		
	}

	public Response<List<Address>> insertOrUpdateAddress(Address address) {
		addressRepository.save(address);
		List<Address> list = addressRepository.findByUsername(address.getUserEntity().getUsername());
		return new Response<List<Address>>(list, 200, "Insert/Update Success");
	}

	public Response<List<Address>> deleteAddress(int id) {
		Address address = addressRepository.findById(id).get();
		addressRepository.deleteById(address.getId());
		List<Address> list = addressRepository.findByUsername(address.getUserEntity().getUsername());
		return new Response<List<Address>>(list, 200, "Delete Success");
	}

	public Response<ClientUserInfoDTO> updateUserInfo(User user) {
		User u = userRepository.findByUsername(user.getUsername());
		if(!user.getPassword().isEmpty()) {
			u.setPassword(user.getPassword());
		}
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		
		return new Response<ClientUserInfoDTO>(null, 200, "Update Success");

	}

	public Response<String> register(RegisterRequestDTO user) {
		User u = new User();
		u.setUsername(user.getUsername());
		u.setRole(user.getRole());
		u.setPassword(user.getPassword());
		u.setPhoneNumber(user.getPhoneNumber());
		u.setName(user.getName());
		userRepository.save(u);
		return new Response<String>("SUCCESS", 200, "Register Success");
	}

	public Response<Boolean> isUserNameExist(String name) {
		return new Response<Boolean>(userRepository.existsByUsername(name), 200, "isUserNameExist");
	}

	public Response<Wishlist> addWishlist(Wishlist wishlist) {
		wishlistRepository.save(wishlist);
		return new Response<>(null, 200, "Add Success");
	}

	public Response<List<Wishlist>> getWishlist(String username) {
		List<Wishlist> list = wishlistRepository.getWishLisByUsername(username);
		return new Response<List<Wishlist>>(list, 200, "Get Success");
	}

	public Response<Wishlist> deleteWishlist(int wishlistId) {
		wishlistRepository.deleteById(wishlistId);
		return new Response<>(null, 200, "Delete Success");
	}

}
