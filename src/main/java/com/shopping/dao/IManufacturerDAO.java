package com.shopping.dao;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Manufacturer;

public interface IManufacturerDAO extends GenericDAO<Manufacturer, Integer> {
	
	List<Manufacturer> findAll();
	
	Optional<Manufacturer> findById(int manufacturerId);
	
	void deleteById(int manufacturerId);
	
}
