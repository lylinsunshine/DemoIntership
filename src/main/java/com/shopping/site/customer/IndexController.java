package com.shopping.site.customer;

import com.shopping.site.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

	private final CategoryService categoryService;
	
	@GetMapping
	public String index(ModelMap map) {
		map.addAttribute("listCategories", categoryService.getCategories());
		return "index";
	}
	
	
}
