package com.shopping.dao;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.ProductImage;

public interface IProductImageDAO extends GenericDAO<ProductImage, Integer> {

	Optional<ProductImage> findById(int productImageId);
	
	List<ProductImage> findListAfterDelete (int displayOrder, int productId);
	
	List<ProductImage> findByProductId (int productId);
}
