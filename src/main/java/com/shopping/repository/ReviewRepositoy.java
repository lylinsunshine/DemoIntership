package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.Review;

public interface ReviewRepositoy extends JpaRepository<Review, Integer>{
	
	List<Review> findAll();
}
