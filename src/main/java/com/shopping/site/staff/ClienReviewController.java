package com.shopping.site.staff;

import com.shopping.site.entity.Review;
import com.shopping.site.service.ReviewService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clients/reviews")
@RequiredArgsConstructor
public class ClienReviewController {

	private final ReviewService reviewService;
	
	@GetMapping("/{productId}")
	public Response<List<Review>> getReviewByProductId(@PathVariable int productId) {
		return reviewService.getReviewByProductId(productId);
	}
	
	@PostMapping("/add-review")
	public Response<Review> addReview(@RequestBody Review review) {
		return reviewService.addReview(review);
	}

}
