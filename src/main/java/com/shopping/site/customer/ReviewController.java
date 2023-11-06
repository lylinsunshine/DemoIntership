package com.shopping.site.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Review;
import com.shopping.service.IReviewService;
import com.shopping.util.PageModel;
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
	
	@GetMapping
	public ResponseModel<PageModel<Review>> getProductPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int rating,
			@RequestParam(required = false) String content) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("rating", rating);
		map.put("content", content);

		return reviewService.findAll(page, size, map);
	}
	
	@GetMapping("/{reviewId}")
	public ResponseModel<Review> calculateProductRating(@PathVariable int reviewId) {
		return reviewService.calculateProductRating(reviewId);
	}

}
