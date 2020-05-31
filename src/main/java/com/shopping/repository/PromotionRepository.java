package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Promotion;

@Repository("promotionRepository")
public interface PromotionRepository extends JpaRepository<Promotion, Integer>, JpaSpecificationExecutor<Promotion> {
	
	@Query(value = "SELECT * FROM `promotion` WHERE start_date<=CURDATE() and end_date>CURDATE()", nativeQuery = true)
	Promotion getCurrentPromotion();
	
}
