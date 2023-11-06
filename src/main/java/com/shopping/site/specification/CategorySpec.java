package com.shopping.site.specification;

import java.util.ArrayList;
import java.util.List;

import com.shopping.site.entity.Category;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpec {
	
	public static Specification<Category> search(String name) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (name != null) {
				predicates.add(builder.like(root.get("name"), "%" + name + "%"));
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
