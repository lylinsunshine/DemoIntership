package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.ICategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		
	}
	
}
