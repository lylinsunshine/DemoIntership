package com.shopping.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.ICategoryDAO;
import com.shopping.entity.Category;
import com.shopping.repository.CategoryRepository;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements ICategoryDAO{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void insertOrUpdate(Category entity) {
		// TODO Auto-generated method stub
		categoryRepository.save(entity);
	}

	@Override
	public void delete(Category entity) {
		// TODO Auto-generated method stub
		categoryRepository.delete(entity);
	}

	@Override
	public List<String> getAllCategoryName() {
		// TODO Auto-generated method stub
		return categoryRepository.getAllCategoryName();
	}

}
