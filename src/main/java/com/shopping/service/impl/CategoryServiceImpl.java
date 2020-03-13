package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.ICategoryDAO;
import com.shopping.entity.Category;
import com.shopping.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<String> getAllCategoriesName() {
		// TODO Auto-generated method stub
		return categoryDAO.getAllCategoriesName();
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryDAO.getAllCategories();
	}

	@Override
	public List<Category> recusiveCategory() {
		// TODO Auto-generated method stub
		return categoryDAO.recusiveCategory();
	}

}
