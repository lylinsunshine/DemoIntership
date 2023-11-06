package com.shopping.dao;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.ProductAttribute;

public interface IProductAttributeDAO extends GenericDAO<ProductAttribute, Integer> {
	
	void deleteById(int productAttributeId);
	
	List<ProductAttribute> findByProductId(int productId);
	
	Optional<ProductAttribute> findById(int productAttributeId);
	
	Boolean isValueExist(String value, int productId);
}
