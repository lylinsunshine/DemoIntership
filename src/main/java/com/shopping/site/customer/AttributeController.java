package com.shopping.site.customer;

import com.shopping.site.entity.Attribute;
import com.shopping.site.service.AttributeService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/attributes")
@RequiredArgsConstructor
public class AttributeController {

	private final AttributeService attributeService;
	
	@GetMapping
	public List<Attribute> getAttributes() {
		return attributeService.getAttributes();
	}
	
	@PostMapping
	public Attribute addAttribute(@RequestBody Attribute attribute) {
		return attributeService.addAttribute(attribute);
	}
	
	@GetMapping("/check-name/{name}")
	public Response<Boolean> isNameExist(@PathVariable String name) {
		return attributeService.isNameExist(name);
	}
}
