package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Review;
import com.shopping.service.IReviewService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private IReviewService reviewService;
	
	@GetMapping("/products/{productId}")
	public ResponseModel<List<Review>> getReviewByProductId(@PathVariable int productId) {
		return reviewService.getReviewByProductId(productId);
	}

}
