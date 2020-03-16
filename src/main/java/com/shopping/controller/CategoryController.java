package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Category;
import com.shopping.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
//	@GetMapping
//	public List<CategoryDTO> getAllCategories(){
//		ModelMapper modelMapper = new ModelMapper();
//		List<CategoryDTO> listCategoryDTO = Arrays.asList(modelMapper.map(categoryService.getAllCategories(), CategoryDTO[].class));
//		return listCategoryDTO;
//	}
	
	@GetMapping
	public List<String> getAllCategories() {
		return categoryService.getAllCategoriesName();
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategory() {
		return null;
	}
	
	
	
}
