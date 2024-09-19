package com.shopping.site.customer;

import com.shopping.site.entity.Review;
import com.shopping.site.service.ReviewService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
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

	private final ReviewService reviewService;
	
	@GetMapping("/products/{productId}")
	public Response<List<Review>> getReviewByProductId(@PathVariable int productId) {
		return reviewService.getReviewByProductId(productId);
	}
	
	@GetMapping
	public Response<PageResponse<Review>> getProductPage(@RequestParam int page, @RequestParam int size,
															  @RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int rating,
															  @RequestParam(required = false) String content) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("rating", rating);
		map.put("content", content);

		return reviewService.findAll(page, size, map);
	}
	
	@GetMapping("/{reviewId}")
	public Response<Review> calculateProductRating(@PathVariable int reviewId) {
		return reviewService.calculateProductRating(reviewId);
	}

}
