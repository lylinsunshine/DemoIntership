package com.shopping.site.dao.impl;

import java.util.List;
import java.util.Map;

import com.shopping.site.repository.AddressRepository;
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
		addressRepository.save(entity);
	}

	@Override
	public void delete(Address entity) {
		addressRepository.delete(entity);
	}

	@Override
	public Page<Address> page(int pageNumber, int pageSize, Map<String, Object> map) {
		return null;
	}

	@Override
	public List<Address> findByUsername(String username) {
		return addressRepository.findByUsername(username);
	}

	@Override
	public void deleteById(int id) {
		addressRepository.deleteById(id);
	}

	@Override
	public Address findById(int id) {
		return addressRepository.findById(id).get();
	}

}
