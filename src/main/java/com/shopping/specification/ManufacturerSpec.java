package com.shopping.specification;

import org.springframework.data.jpa.domain.Specification;

import com.shopping.entity.Manufacturer;

public class ManufacturerSpec {
	
	public static Specification<Manufacturer> hasName(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}
	
	public static Specification<Manufacturer> hasDescription(String description) {
		return (root, query, builder) -> builder.like(root.get("description"), "%" + description + "%");
	}
	
	public static Specification<Manufacturer> hasAddress(String address) {
		return (root, query, builder) -> builder.like(root.get("address"), "%" + address + "%");
	}
	
	
}
