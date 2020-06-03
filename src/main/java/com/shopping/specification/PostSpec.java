package com.shopping.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.shopping.entity.Post;

@Component
public class PostSpec {
	public static Specification<Post> search(String name) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (name != null) {
				predicates.add(builder.like(root.get("title"), "%" + name + "%"));
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
