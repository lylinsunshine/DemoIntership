package com.shopping.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi.PKCS1v1_5Padding_PublicOnly;
import org.springframework.data.jpa.domain.Specification;

import com.shopping.entity.Manufacturer;

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
