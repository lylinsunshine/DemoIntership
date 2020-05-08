package com.shopping.service;

import java.util.List;

import com.shopping.entity.Category;

public interface ICategoryService {
	
	List<String> getAllCategoriesName();
	
	List<Category> getAllCategories();
	
	List<Category> recusiveCategory();
	
	List<Category> findAll();
}
