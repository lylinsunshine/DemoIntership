package com.shopping.service;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Manufacturer;

public interface IManufacturerService {

	List<Manufacturer> findAll();
	
	Optional<Manufacturer> findById(int manufacturerId);
	
	void add(Manufacturer manufacturer);
	
	void deleteById(int manufacturerId);
	
	void update(Manufacturer manufacturer);
}
