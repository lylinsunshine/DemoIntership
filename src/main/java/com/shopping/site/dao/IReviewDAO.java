package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Review;

public interface IReviewDAO extends GenericDAO<Review, Integer> {
	
	List<Review> findAll();
	
	List<Review> getReviewByProductId(int productId);
}
