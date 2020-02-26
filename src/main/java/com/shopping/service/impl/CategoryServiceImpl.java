package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.ICategoryDAO;
import com.shopping.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<String> getAllCategoryName() {
		// TODO Auto-generated method stub
		return categoryDAO.getAllCategoryName();
	}

}
