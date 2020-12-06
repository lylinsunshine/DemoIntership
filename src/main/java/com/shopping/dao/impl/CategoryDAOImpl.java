package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.ICategoryDAO;
import com.shopping.entity.Category;
import com.shopping.repository.CategoryRepository;
import com.shopping.specification.CategorySpec;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements ICategoryDAO{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void insertOrUpdate(Category entity) {
		categoryRepository.save(entity);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public List<String> getAllCategoriesName() {
		return categoryRepository.getAllCategoryName();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> recusiveCategory() {
		return categoryRepository.recusiveCategory();
	}

	@Override
	public Page<Category> page(int pageNumber, int pageSize, Map<String, Object> map) {
		String name = (String) map.get("name");
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Category> spec = CategorySpec.search(name);
		return categoryRepository.findAll(spec, pageable);
	}

	@Override
	public List<Category> getAllCategoriesNotHaveParent() {
		List<Category> list = categoryRepository.findAll();
		list.removeIf(category -> category.getParent()!=null);
		return list;
	}

	@Override
	public Category findById(int id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public boolean isNameExist(String name) {
		return categoryRepository.existsByName(name);
	}

	@Override
	public boolean isUrlExist(String url) {
		return categoryRepository.existsByUrl(url);
	}

	@Override
	public int isCategoryHaveChild(int id) {
		return categoryRepository.isCategoryHaveChild(id);
	}

	@Override
	public List<Category> findAllSubCategory(int id) {
		return categoryRepository.findAllSubCategory(id);
	}

	@Override
	public Category findByUrl(String url) {
		return categoryRepository.findByUrl(url);
	}

}
