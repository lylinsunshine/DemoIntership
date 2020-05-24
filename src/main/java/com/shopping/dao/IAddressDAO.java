package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Address;

public interface IAddressDAO extends GenericDAO<Address, Integer>  {
		
	List<Address> findByUsername(String username);
	
	void deleteById(int id);
	
	Address findById(int id);
}
