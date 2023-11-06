package com.shopping.site.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shopping.site.entity.Promotion;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class PromotionSpec {
	
	public static Specification<Promotion> search(String name, Date startDate, Date endDate, String type) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (name != null) {
				predicates.add(builder.like(root.get("name"), "%" + name + "%"));
			}
			if (type != null) {
				predicates.add(builder.equal(root.get("type"), type));
			}
			if (startDate != null && endDate == null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), startDate));
			}
			if (startDate == null && endDate != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("endDate"), endDate));
			}
			if (startDate != null && endDate != null) {
				predicates.add(
						builder.and(
								builder.greaterThanOrEqualTo(root.get("startDate"), startDate),
								builder.lessThanOrEqualTo(root.get("endDate"), endDate)
								)
						);
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
}
