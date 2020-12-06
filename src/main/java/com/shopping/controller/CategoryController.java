package com.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Category;
import com.shopping.service.ICategoryService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
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
	
	@GetMapping("/select")
	public List<Category> getAllCategories2() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/all")
	public ResponseModel<PageModel<Category>> getCategoryPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return categoryService.findAll(page, size, map);
	}
	
	@GetMapping("/all-no-parent")
	public List<Category> getAllCategoriesNotHaveParent() {
		return categoryService.getAllCategoriesNotHaveParent();
	}
	
	@PostMapping("/update")
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
	@PostMapping("/add")
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/checkname/{name}")
	public ResponseModel<Boolean> isNameExist(@PathVariable String name) {
		return categoryService.isNameExist(name);
	}
	
	@GetMapping("/checkurl/{url}")
	public ResponseModel<Boolean> isUrlExist(@PathVariable String url) {
		return categoryService.isUrlExist(url);
	}
	
	@GetMapping("/check-have-child/{id}")
	public ResponseModel<Integer> isCategoryHaveChild(@PathVariable int id) {
		return categoryService.isCategoryHaveChild(id);
	}
	

}
