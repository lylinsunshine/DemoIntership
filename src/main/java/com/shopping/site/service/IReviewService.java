package com.shopping.service;

import java.util.List;
import java.util.Map;

import com.shopping.entity.Review;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IReviewService {
	
	ResponseModel<List<Review>> getReviewByProductId(int productId);

	ResponseModel<Review> addReview(Review review);
	
	ResponseModel<PageModel<Review>> findAll(int pageNumber, int pageSize, Map<String, Object> map);

	ResponseModel<Review> calculateProductRating(int reviewId);
}
