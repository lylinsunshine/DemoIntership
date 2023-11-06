package com.shopping.site.repository;

import com.shopping.site.entity.PromotionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("promotionProductRepository")
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Integer> {

}
