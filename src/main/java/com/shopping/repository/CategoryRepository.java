package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("SELECT c.name FROM Category c")
	List<String> getAllCategoryName();
	
	List<Category> findAll();
}
