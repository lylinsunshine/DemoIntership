package com.shopping.site.customer;

import com.shopping.entity.Review;
import com.shopping.service.IReviewService;
import com.shopping.site.entity.Review;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

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
