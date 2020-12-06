package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IReviewDAO;
import com.shopping.entity.Review;
import com.shopping.repository.ReviewRepositoy;
import com.shopping.specification.ReviewSpec;

@Repository("reviewDAO")
@Transactional
public class ReviewDAOImpl implements IReviewDAO {
	
	@Autowired
	private ReviewRepositoy reviewRepository;

	@Override
	public void insertOrUpdate(Review entity) {
		reviewRepository.save(entity);
	}

	@Override
	public void delete(Review entity) {
		reviewRepository.delete(entity);
	}

	@Override
	public List<Review> findAll() {
		return reviewRepository.findAll();
	}

	@Override
	public Page<Review> page(int pageNumber, int pageSize, Map<String, Object> map) {

		String name = (String) map.get("name");
		int rating = (int) map.get("rating");
		String content = (String) map.get("content");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
		Specification<Review> spec = ReviewSpec.search(name, rating, content);
		return reviewRepository.findAll(spec, pageable);
	}

	@Override
	public List<Review> getReviewByProductId(int productId) {
		return reviewRepository.findByProductId(productId);
	}

}
