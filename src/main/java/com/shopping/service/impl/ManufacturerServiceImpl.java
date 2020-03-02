package com.shopping.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.IManufacturerDAO;
import com.shopping.entity.Manufacturer;
import com.shopping.service.IManufacturerService;

@Service
public class ManufacturerServiceImpl implements IManufacturerService {

	@Autowired
	private IManufacturerDAO manufacturerDAO; 
	
	@Override
	public List<Manufacturer> findAll() {
		// TODO Auto-generated method stub
		return manufacturerDAO.findAll();
	}

	@Override
	public Optional<Manufacturer> findById(int manufacturerId) {
		// TODO Auto-generated method stub
		return manufacturerDAO.findById(manufacturerId);
	}

	@Override
	public void add(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		manufacturerDAO.insertOrUpdate(manufacturer);
	}

	@Override
	public void deleteById(int manufacturerId) {
		// TODO Auto-generated method stub
		manufacturerDAO.deleteById(manufacturerId);
	}

	@Override
	public void update(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		manufacturerDAO.insertOrUpdate(manufacturer);
	}
	
	

}
