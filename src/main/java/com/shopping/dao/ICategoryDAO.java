package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Category;

public interface ICategoryDAO extends GenericDAO<Category, Integer>{
		
	List<String> getAllCategoryName();
}
