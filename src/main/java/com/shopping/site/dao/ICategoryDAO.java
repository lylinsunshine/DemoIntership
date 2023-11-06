package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Category;

public interface ICategoryDAO extends GenericDAO<Category, Integer>{
		
	List<String> getAllCategoriesName();
	
	List<Category> getAllCategories();
	
	List<Category> recusiveCategory();
	
	List<Category> getAllCategoriesNotHaveParent();
	
	Category findById(int id);
	
	boolean isNameExist(String name);
	
	boolean isUrlExist(String url);
	
	int isCategoryHaveChild(int id);
	
	List<Category> findAllSubCategory(int id);
	
	Category findByUrl(String url);
}
