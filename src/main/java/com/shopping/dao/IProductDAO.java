package com.shopping.dao;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Product;

public interface IProductDAO extends GenericDAO<Product, Integer>{

	List<String> getAllProductName();
	
	List<Product> getAllProduct();
	
	Optional<Product> findById(int productId);
	
	void deleteById(int productId);
	
}
