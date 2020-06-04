package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Attribute;
import com.shopping.service.IAttributeService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/attributes")
public class AttributeController {
	
	@Autowired
	private IAttributeService attributeService;
	
	@GetMapping("/select")
	public List<Attribute> getAllAttributes() {
		return attributeService.getAllAttributes();
	}
	
	@PostMapping("/add")
	public List<Attribute> addAttribute(@RequestBody Attribute attribute) {
		return attributeService.addAttribute(attribute);
	}
	
	@GetMapping("/checkname/{name}")
	public ResponseModel<Boolean> isNameExist(@PathVariable String name) {
		return attributeService.isNameExist(name);
	}
}
