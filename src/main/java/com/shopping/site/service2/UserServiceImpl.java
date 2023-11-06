package com.shopping.site.service2;

import com.shopping.dao.IAddressDAO;
import com.shopping.dao.IUserDAO;
import com.shopping.dto.ClientUserInfoDTO;
import com.shopping.dto.LoginRequestDTO;
import com.shopping.dto.RegisterRequestDTO;
import com.shopping.entity.Address;
import com.shopping.entity.User;
import com.shopping.entity.Wishlist;
import com.shopping.repository.UserRepository;
import com.shopping.repository.WishlistRepository;
import com.shopping.service.IUserService;
import com.shopping.util.ResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IAddressDAO addressDAO; 
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	// @Autowired
	// private ModelMapper modelMapper;

	@Override
	public ResponseModel<String> login(LoginRequestDTO user) {
		String token = "";
		User u = userDAO.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (u!=null) {
			if(u.getRole().equals(user.getRole()))
				token = user.getUsername();
		}
		return new ResponseModel<String>(token, HttpStatus.OK, "Login Response");
	}

	@Override
	public ResponseModel<ClientUserInfoDTO> getUserInfo(String username) {
		ModelMapper modelMapper = new ModelMapper();
		User u = userDAO.findByUsername(username);
		if(u!=null) {
			ClientUserInfoDTO dto = modelMapper.map(u, ClientUserInfoDTO.class);
			return new ResponseModel<ClientUserInfoDTO>(dto, HttpStatus.OK, "Get Success");
		} else {
			return new ResponseModel<ClientUserInfoDTO>(null, HttpStatus.OK, "Get Fail");
		}
		
	}

	@Override
	public ResponseModel<List<Address>> insertOrUpdateAddress(Address address) {
		addressDAO.insertOrUpdate(address);
		List<Address> list = addressDAO.findByUsername(address.getUserEntity().getUsername());
		return new ResponseModel<List<Address>>(list, HttpStatus.OK, "Insert/Update Success");
	}

	@Override
	public ResponseModel<List<Address>> deleteAddress(int id) {
		Address address = addressDAO.findById(id);
		addressDAO.deleteById(address.getId()); 
		List<Address> list = addressDAO.findByUsername(address.getUserEntity().getUsername());
		return new ResponseModel<List<Address>>(list, HttpStatus.OK, "Delete Success");
	}

	@Override
	public ResponseModel<ClientUserInfoDTO> updateUserInfo(User user) {
		User u = userDAO.findByUsername(user.getUsername());
		if(!user.getPassword().isEmpty()) {
			u.setPassword(user.getPassword());
		}
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		
		return new ResponseModel<ClientUserInfoDTO>(null, HttpStatus.OK, "Update Success");

	}

	@Override
	public ResponseModel<String> register(RegisterRequestDTO user) {
		User u = new User();
		u.setUsername(user.getUsername());
		u.setRole(user.getRole());
		u.setPassword(user.getPassword());
		u.setPhoneNumber(user.getPhoneNumber());
		u.setName(user.getName());
		userRepository.save(u);
		return new ResponseModel<String>("SUCCESS", HttpStatus.OK, "Register Success");
	}

	@Override
	public ResponseModel<Boolean> isUserNameExist(String name) {
		return new ResponseModel<Boolean>(userRepository.existsByUsername(name), HttpStatus.OK, "isUserNameExist");
	}

	@Override
	public ResponseModel<Wishlist> addWishlist(Wishlist wishlist) {
		wishlistRepository.save(wishlist);
		return new ResponseModel<Wishlist>(null, HttpStatus.OK, "Add Success");
	}

	@Override
	public ResponseModel<List<Wishlist>> getWishlist(String username) {
		List<Wishlist> list = wishlistRepository.getWishLisByUsername(username);
		return new ResponseModel<List<Wishlist>>(list, HttpStatus.OK, "Get Success");
	}

	@Override
	public ResponseModel<Wishlist> deleteWishlist(int wishlistId) {
		wishlistRepository.deleteById(wishlistId);
		return new ResponseModel<Wishlist>(null, HttpStatus.OK, "Delete Success");
	}

}
