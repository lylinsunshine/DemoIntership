package com.shopping.clientcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Review;
import com.shopping.service.IReviewService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/reviews")
public class ClienReviewController {
	
	@Autowired
	private IReviewService reviewService;
	
	@GetMapping("/{productId}")
	public ResponseModel<List<Review>> getReviewByProductId(@PathVariable int productId) {
		return reviewService.getReviewByProductId(productId);
	}
	
	@PostMapping("/add-review")
	public ResponseModel<Review> addReview(@RequestBody Review review) {
		return reviewService.addReview(review);
	}

}
