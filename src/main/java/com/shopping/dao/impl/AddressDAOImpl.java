package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IAddressDAO;
import com.shopping.entity.Address;
import com.shopping.repository.AddressRepository;

@Repository("addressDAO")
@Transactional
public class AddressDAOImpl implements IAddressDAO {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void insertOrUpdate(Address entity) {
		// TODO Auto-generated method stub
		addressRepository.save(entity);
	}

	@Override
	public void delete(Address entity) {
		// TODO Auto-generated method stub
		addressRepository.delete(entity);
	}

	@Override
	public Page<Address> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findByUsername(String username) {
		// TODO Auto-generated method stub
		return addressRepository.findByUsername(username);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		addressRepository.deleteById(id);
	}

	@Override
	public Address findById(int id) {
		// TODO Auto-generated method stub
		return addressRepository.findById(id).get();
	}

}
