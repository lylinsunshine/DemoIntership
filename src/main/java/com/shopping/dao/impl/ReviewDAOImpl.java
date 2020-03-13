package com.shopping.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IReviewDAO;
import com.shopping.entity.Review;
import com.shopping.repository.ReviewRepositoy;

@Repository("reviewDAO")
@Transactional
public class ReviewDAOImpl implements IReviewDAO {
	
	@Autowired
	private ReviewRepositoy reviewRepository;

	@Override
	public void insertOrUpdate(Review entity) {
		// TODO Auto-generated method stub
		reviewRepository.save(entity);
	}

	@Override
	public void delete(Review entity) {
		// TODO Auto-generated method stub
		reviewRepository.delete(entity);
	}

	@Override
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

	@Override
	public Page<Review> page(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
