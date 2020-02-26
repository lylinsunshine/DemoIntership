package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Product;

public interface IProductDAO extends GenericDAO<Product, Integer>{

	List<String> getAllProductName();
	
}
