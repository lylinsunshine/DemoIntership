package com.shopping.site.customer;

import java.util.List;

import com.shopping.site.entity.Category;
import com.shopping.site.service.CategoryService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	@GetMapping("/name")
	public List<String> getCategoriesName() {
		return categoryService.getCategoriesName();
	}
	
	@GetMapping
	public List<Category> getCategories() {
		return categoryService.getCategories();
	}
	
	@GetMapping("/search")
	public Response<PageResponse<Category>> getCategoryPage(@RequestParam int page, @RequestParam int size,
															 @RequestParam(required = false) String name) {
		return categoryService.page(page, size, name);
	}
	
	@GetMapping("/all-no-parent")
	public List<Category> getAllCategoriesNotHaveParent() {
		return categoryService.getCategoriesNotHaveParent();
	}
	
	@PutMapping
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
	@PostMapping
	public Response<Category> addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/check-name/{name}")
	public Response<Boolean> isNameExist(@PathVariable String name) {
		return categoryService.isNameExist(name);
	}
	
	@GetMapping("/check-url/{url}")
	public Response<Boolean> isUrlExist(@PathVariable String url) {
		return categoryService.isUrlExist(url);
	}
	
	@GetMapping("/check-child/{id}")
	public Response<Integer> isCategoryHaveChild(@PathVariable int id) {
		return categoryService.isCategoryHaveChild(id);
	}
}
