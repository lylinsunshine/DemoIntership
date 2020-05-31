package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.PromotionProduct;

@Repository("promotionProductRepository")
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Integer> {

}
