package com.shopping.site.service;

import com.shopping.site.entity.Product;
import com.shopping.site.entity.Review;
import com.shopping.site.repository.ProductRepository;
import com.shopping.site.repository.ReviewRepositoy;
import com.shopping.site.specification.ReviewSpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepositoy reviewRepository;

	private final ProductRepository productRepository;

	public Response<List<Review>> getReviewByProductId(int productId) {
		List<Review> list = reviewRepository.findByProductId(productId);
		return new Response<List<Review>>(list, 200, "Get Success");
	}

	public Response<Review> addReview(Review review) {
		reviewRepository.save(review);
		return new Response<Review>(null, 200, "Get Success");
	}

	public Response<PageResponse<Review>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {

		String name = (String) map.get("name");
		int rating = (int) map.get("rating");
		String content = (String) map.get("content");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
		Specification<Review> spec = ReviewSpec.search(name, rating, content);

		Page<Review> page = reviewRepository.findAll(spec, pageable);
		PageResponse<Review> pageModel = new PageResponse<Review>(page.getContent(), pageNumber, page.getTotalPages());
		return new Response<PageResponse<Review>>(pageModel, 200, "All reviews");
	}

	public Response<Review> calculateProductRating(int reviewId) {
		Review r = reviewRepository.findById(reviewId).get();
		r.setApproved(!r.isApproved());
		int productId = r.getProductEntity().getId();
		List<Review> list = reviewRepository.findByProductId(productId);
		if(list.size()!=0) {
			Double[] countStar = {0.0, 0.0, 0.0, 0.0, 0.0};
			for (int i = 1; i < 6; i++) {
				Double temp = 0.0;
				for (Review review : list) {
					if(review.getRating()==i) {
						temp++;
					}
				}
				countStar[i-1]=temp;
			}
			
			Double productRating = (double) ((5*countStar[4]+4*countStar[3]+3*countStar[2]+2*countStar[1]+1*countStar[0])/ list.size());
			Product p = productRepository.findById(productId).get();
			p.setRating(Double.parseDouble(new DecimalFormat("##.#").format(productRating)));
			productRepository.save(p);
		} else {
			Product p = productRepository.findById(productId).get();
			p.setRating(5.0);
			productRepository.save(p);
		}
		
		return new Response<Review>(null, 200, "All reviews");
	}
	
}
