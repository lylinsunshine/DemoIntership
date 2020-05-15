package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.ProductAttribute;

@Repository("productAttributeRepository")
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
	
	@Query("FROM ProductAttribute p where p.productEntity.id = ?1")
	List<ProductAttribute> findByProductId(int productId);
	
	@Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM ProductAttribute p where p.value=?1 and p.productEntity.id = ?2")
	boolean existsByValueAndProductId(String value, int productId);
}
