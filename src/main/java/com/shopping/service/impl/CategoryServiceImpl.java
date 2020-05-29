package com.shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopping.dao.ICategoryDAO;
import com.shopping.dto.ProductDTO;
import com.shopping.entity.Category;
import com.shopping.entity.Product;
import com.shopping.service.ICategoryService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
@Transactional
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

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseModel<PageModel<Category>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Category> page = categoryDAO.page(pageNumber, pageSize, map);
		PageModel<Category> pageModel = new PageModel<Category>(page.getContent(), pageNumber, page.getTotalPages());
		return new ResponseModel<PageModel<Category>>(pageModel, HttpStatus.OK, "All categories");
	}

	@Override
	public List<Category> getAllCategoriesNotHaveParent() {
		// TODO Auto-generated method stub
		return categoryDAO.getAllCategoriesNotHaveParent();
	}

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDAO.insertOrUpdate(category);
		return category;
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
//		int id = category.getId();
//		Category c = categoryDAO.findById(id);
//		c.setName(category.getName());
//		c.setDescription(category.getDescription());
//		c.getParent().setId(category.getParent().getId());
		categoryDAO.insertOrUpdate(category);
		return null;
	}

	@Override
	public ResponseModel<Boolean> isNameExist(String name) {
		// TODO Auto-generated method stub
		boolean isNameExist = categoryDAO.isNameExist(name);
		return new ResponseModel<Boolean>(isNameExist, HttpStatus.OK, "isNameExist");
	}

	@Override
	public ResponseModel<Boolean> isUrlExist(String url) {
		// TODO Auto-generated method stub
		boolean isUrlExist = categoryDAO.isUrlExist(url);
		return new ResponseModel<Boolean>(isUrlExist, HttpStatus.OK, "isUrlExist");
	}

	@Override
	public ResponseModel<Integer> isCategoryHaveChild(int id) {
		// TODO Auto-generated method stub
		int count = categoryDAO.isCategoryHaveChild(id);
		return new ResponseModel<Integer>(count, HttpStatus.OK, "isCategoryHaveChild");
	}

}
