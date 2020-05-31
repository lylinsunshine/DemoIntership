package com.shopping.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.shopping.entity.Category;
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

	public static Specification<Product> clientSearch(String name, int priceFrom, int priceTo, int manufacturerId,
			int categoryId, int initCategoryId) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			// builder.in(builder.or(builder.equal(root.get("categoryEntity").get("id"),
			// categoryId),
			// builder.equal(root.get("categoryEntity").get("parent").get("id"),
			// categoryId)))
			Subquery<Category> subquery = query.subquery(Category.class);
			Root<Category> rootCategory = subquery.from(Category.class);
//			builder.in(
//					root.get("categoryEntity").get("id")).value(					
////							builder.or(
////									builder.equal(rootCategory.get("id"), initCategoryId), 
////									builder.equal(rootCategory.get("parent").get("id"), initCategoryId)
////									)
//							)
//			); 
			subquery.select(rootCategory.get("id")).where(builder.or(builder.equal(rootCategory.get("id"), initCategoryId),
					builder.equal(rootCategory.get("parent").get("id"), initCategoryId)));
			predicates.add(
					builder.in(root.get("categoryEntity").get("id")).value(subquery)
					);
			//builder.exists(subquery);
			if (name != null) {
				predicates.add(builder.like(root.get("name"), "%" + name + "%"));
			}
			if (manufacturerId != 0) {
				predicates.add(builder.equal(root.get("manufacturerEntity").get("id"), manufacturerId));
			}
			if (categoryId != 0) {
				predicates.add(builder.equal(root.get("categoryEntity").get("id"), categoryId));
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
		public static Specification<Product> clientSearchPage(String name, int priceFrom, int priceTo, int manufacturerId,
				int categoryId) {
			return (root, query, builder) -> {
				List<Predicate> predicates = new ArrayList<>();
				if (name != null) {
					predicates.add(builder.like(root.get("name"), "%" + name + "%"));
				}
				if (manufacturerId != 0) {
					predicates.add(builder.equal(root.get("manufacturerEntity").get("id"), manufacturerId));
				}
				if (categoryId != 0) {
					predicates.add(builder.equal(root.get("categoryEntity").get("id"), categoryId));
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
