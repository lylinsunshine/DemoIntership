package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.ProductImage;

@Repository("productImageRepository")
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
	
	@Query("FROM ProductImage p where p.productEntity.id = ?1")
	List<ProductImage> findByProductId(int productId);
	
	@Query("FROM ProductImage p where p.displayOrder > ?1 and p.productEntity.id = ?2")
	List<ProductImage> findListAfterDelete(int displayOrder, int productId);
}
