package com.shopping.service;

import java.util.List;

import com.shopping.entity.Review;
import com.shopping.util.ResponseModel;

public interface IReviewService {
	
	ResponseModel<List<Review>> getReviewByProductId(int productId);
}
