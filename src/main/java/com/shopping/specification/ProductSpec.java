package com.shopping.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.shopping.entity.Manufacturer;
import com.shopping.entity.Product;

@Component
public class ProductSpec {

	public static Specification<Product> hasName(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}

	public static Specification<Product> hasPriceFromTo(int priceFrom, int priceTo) {
		return (root, query, builder) -> builder.between(root.get("name"), priceFrom, priceTo);
	}

	public static Specification<Product> hasManufacturer(String manufacturerId) {
		return (root, query, builder) -> {
			Join<Product, Manufacturer> joinProdManu = root.join("manufacturerEntity");
			return builder.equal(joinProdManu.get("name"), manufacturerId);
		};
	}

	public static Specification<Product> search(String name, int priceFrom, int priceTo, int manufacturerId) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (name != null) {
				predicates.add(builder.like(root.get("name"), "%" + name + "%"));
			}
			if (manufacturerId != 0) {
				predicates.add(builder.equal(root.get("manufacturerEntity").get("id"), manufacturerId));
			}
			if (priceFrom != 0 && priceTo == 0) {
				predicates.add(builder.ge(root.get("price"), priceFrom));
			}
			if (priceFrom == 0 && priceTo != 0) {
				predicates.add(builder.le(root.get("price"), priceTo));
			}
			if (priceFrom != 0 && priceTo != 0) {
				predicates.add(builder.between(root.get("price"), priceFrom, priceTo));
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
