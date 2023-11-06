package com.shopping.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.shopping.entity.Product;

public interface IProductDAO extends GenericDAO<Product, Integer>{

	List<String> getAllProductName();
	
	List<Product> getAllProduct();
	
	Optional<Product> findById(int productId);
	
	void deleteById(int productId);
	
	boolean isNameExist(String name);
	
	boolean isSkuExist(String sku);
	
	boolean isUrlExist(String url);
	
	Product findByUrl(String url);
	
	Page<Product> clientPage(int pageNumber, int pageSize, Map<String, Object> map);
	
	Page<Product> clientPage(int pageNumber, int pageSize, Map<String, Object> map, int initCategoryId);
	
	List<Product> findAllByParentCategory(int categoryId);
}
