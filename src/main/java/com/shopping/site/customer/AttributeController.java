package com.shopping.site.customer;

import java.util.List;

import com.shopping.site.service2.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.site.entity.Attribute;
import com.shopping.site.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/attributes")
@RequiredArgsConstructor
public class AttributeController {
	
	@Autowired
	private final AttributeService attributeService;
	
	@GetMapping
	public List<Attribute> getAttributes() {
		return attributeService.getAttributes();
	}
	
	@PostMapping
	public Attribute addAttribute(@RequestBody Attribute attribute) {
		return attributeService.addAttribute(attribute);
	}
	
	@GetMapping("/checkname/{name}")
	public ResponseModel<Boolean> isNameExist(@PathVariable String name) {
		return attributeService.isNameExist(name);
	}
}
