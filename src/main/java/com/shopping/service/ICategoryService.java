package com.shopping.service;

import java.util.List;
import java.util.Map;

import com.shopping.entity.Category;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface ICategoryService {
	
	List<String> getAllCategoriesName();
	
	List<Category> getAllCategories();
	
	List<Category> recusiveCategory();
	
	List<Category> findAll();
	
	ResponseModel<PageModel<Category>> findAll(int pageNumber, int pageSize, Map<String, Object> map);
	
	List<Category> getAllCategoriesNotHaveParent();
	
	Category updateCategory(Category category);
	
	Category addCategory(Category category);
	
	ResponseModel<Boolean> isNameExist(String name);
	
	ResponseModel<Boolean> isUrlExist(String url);
	
	ResponseModel<Integer> isCategoryHaveChild(int id);
}
