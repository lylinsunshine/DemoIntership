package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Attribute;
import com.shopping.entity.Category;
import com.shopping.service.IAttributeService;
import com.shopping.service.IProductService;

@CrossOrigin
@RestController
@RequestMapping("/attributes")
public class AttributeController {
	
	@Autowired
	private IAttributeService attributeService;
	
	@GetMapping("/select")
	public List<Attribute> getAllCategories2() {
		return attributeService.getAllAttributes();
	}
}
