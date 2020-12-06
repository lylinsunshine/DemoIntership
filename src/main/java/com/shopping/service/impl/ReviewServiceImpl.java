package com.shopping.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IProductDAO;
import com.shopping.dao.IReviewDAO;
import com.shopping.entity.Product;
import com.shopping.entity.Review;
import com.shopping.repository.ReviewRepositoy;
import com.shopping.service.IReviewService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class ReviewServiceImpl implements IReviewService {
		
	@Autowired
	private IReviewDAO reviewDAO;
	
	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private ReviewRepositoy reviewRepository;

	@Override
	public ResponseModel<List<Review>> getReviewByProductId(int productId) {
		List<Review> list = reviewDAO.getReviewByProductId(productId);
		return new ResponseModel<List<Review>>(list, HttpStatus.OK, "Get Success");
	}

	@Override
	public ResponseModel<Review> addReview(Review review) {
		reviewDAO.insertOrUpdate(review);
		return new ResponseModel<Review>(null, HttpStatus.OK, "Get Success");
	}

	@Override
	public ResponseModel<PageModel<Review>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		Page<Review> page = reviewDAO.page(pageNumber, pageSize, map);
		PageModel<Review> pageModel = new PageModel<Review>(page.getContent(), pageNumber, page.getTotalPages());
		return new ResponseModel<PageModel<Review>>(pageModel, HttpStatus.OK, "All reviews");
	}

	@Override
	public ResponseModel<Review> calculateProductRating(int reviewId) {
		Review r = reviewRepository.findById(reviewId).get();
		r.setApproved(!r.isApproved());
		int productId = r.getProductEntity().getId();
		List<Review> list = reviewDAO.getReviewByProductId(productId);
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
			//System.out.println(productRating);
			Product p = productDAO.findById(productId).get();
			//System.out.println(p.getName());
			p.setRating(Double.parseDouble(new DecimalFormat("##.#").format(productRating)));
			productDAO.insertOrUpdate(p);
		} else {
			Product p = productDAO.findById(productId).get();
			p.setRating(5.0);
			productDAO.insertOrUpdate(p);
		}
		
		return new ResponseModel<Review>(null, HttpStatus.OK, "All reviews");
	}
	
}
