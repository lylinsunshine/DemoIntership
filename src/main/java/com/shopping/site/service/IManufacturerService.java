package com.shopping.service;

import java.util.List;
import java.util.Map;

import com.shopping.entity.Manufacturer;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IManufacturerService {
	
	ResponseModel<PageModel<Manufacturer>> findAll(int pageNumber, int pageSize, Map<String, Object> map);
	
//	ResponseModel<List<Manufacturer>> findAll();

	List<Manufacturer> findAll();
	
//	Optional<Manufacturer> findById(int manufacturerId);
	
	ResponseModel<Manufacturer> findById(int manufacturerId);
	
	ResponseModel<Manufacturer> add(Manufacturer manufacturer);
	
	ResponseModel<Manufacturer> deleteById(int manufacturerId);
	
	ResponseModel<Manufacturer> update(Manufacturer manufacturer);
}
