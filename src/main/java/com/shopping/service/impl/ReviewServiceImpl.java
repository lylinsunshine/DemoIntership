package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.IReviewDAO;
import com.shopping.service.IReviewService;

@Service
public class ReviewServiceImpl implements IReviewService {
		
	@Autowired
	private IReviewDAO reviewDAO;
	
}
