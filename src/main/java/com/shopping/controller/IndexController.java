package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.service.ICategoryService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping
	public String index(ModelMap map) {
		map.addAttribute("listCategories", categoryService.getAllCategoryName());
		return "index";
	}
	
	
}
