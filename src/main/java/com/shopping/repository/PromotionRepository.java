package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Promotion;

@Repository("promotionRepository")
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
	
}
