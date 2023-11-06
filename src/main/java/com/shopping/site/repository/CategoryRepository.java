package com.shopping.site.repository;

import java.util.List;

import com.shopping.site.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.dto.TotalProductPerCategorChartDTO;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{
	
	@Query("SELECT c.name FROM Category c")
	List<String> getAllCategoryName();

	@Query("select c from Category c left join fetch c.children")
	List<Category> recusiveCategory();
	
	boolean existsByName(String name);
	
	boolean existsByUrl(String url);
	
	@Query("select count(c) from Category c where c.parent.id= ?1")
	int isCategoryHaveChild(int id);
	
	@Query(value = "SELECT * FROM category WHERE parent_id = ?1 OR category_id = ?1", nativeQuery = true)
	List<Category> findAllSubCategory(int id);
	
	Category findByUrl(String url);
	
	@Query(value = "SELECT COUNT(category_id) FROM category ", nativeQuery = true)
	int getTotalCategory();
	
	@Query(value = "SELECT category.`name` as name, count(product_id) as total FROM category INNER JOIN product ON product.category_id = category.category_id GROUP BY category.`name`, category.category_id ORDER BY name asc", nativeQuery = true)
	List<TotalProductPerCategorChartDTO> getTotalProduct();
	
}
