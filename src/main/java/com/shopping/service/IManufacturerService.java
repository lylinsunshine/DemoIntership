package com.shopping.service;

import java.util.List;
import java.util.Optional;

import com.shopping.dto.ListDTO;
import com.shopping.entity.Manufacturer;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IManufacturerService {
	
	ResponseModel<PageModel<Manufacturer>> findAll(int pageNumber, int pageSize);
	
//	ResponseModel<List<Manufacturer>> findAll();

//	List<Manufacturer> findAll();
//	
//	Optional<Manufacturer> findById(int manufacturerId);
//	
	ResponseModel<Manufacturer> add(Manufacturer manufacturer);
//	
//	void deleteById(int manufacturerId);
//	
//	void update(Manufacturer manufacturer);
}
