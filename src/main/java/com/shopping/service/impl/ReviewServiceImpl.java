package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IReviewDAO;
import com.shopping.entity.Review;
import com.shopping.service.IReviewService;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class ReviewServiceImpl implements IReviewService {
		
	@Autowired
	private IReviewDAO reviewDAO;

	@Override
	public ResponseModel<List<Review>> getReviewByProductId(int productId) {
		// TODO Auto-generated method stub
		List<Review> list = reviewDAO.getReviewByProductId(productId);
		return new ResponseModel<List<Review>>(list, HttpStatus.OK, "Get Success");
	}
	
}
