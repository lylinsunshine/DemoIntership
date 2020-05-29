package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Category;

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
}
