package com.shopping.site.specification;

import java.util.ArrayList;
import java.util.List;

import com.shopping.site.entity.Manufacturer;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerSpec {

	public static Specification<Manufacturer> hasName(String name) {
		return (root, query, builder) -> name == null ? null : builder.like(root.get("name"), "%" + name + "%");
	}

	public static Specification<Manufacturer> hasAddress(String address) {
		return (root, query, builder) -> address == null ? null
				: builder.like(root.get("address"), "%" + address + "%");
	}

	public static Specification<Manufacturer> hasNameAndAdress(String name, String address) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (name != null) {
				predicates.add(builder.like(root.get("name"), "%" + name + "%"));
			}
			if (address != null) {
				predicates.add(builder.like(root.get("address"), "%" + address + "%"));
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
