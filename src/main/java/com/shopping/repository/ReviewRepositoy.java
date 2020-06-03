package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Review;

@Repository("reviewRepository")
public interface ReviewRepositoy extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review>{
	
	List<Review> findAll();
	
	@Query("FROM Review r where r.productEntity.id = ?1 and r.isApproved=true order by r.id desc ")
	List<Review> findByProductId(int productId);
}
