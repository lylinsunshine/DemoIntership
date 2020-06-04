package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Attribute;

@Repository("attributeRepository")
public interface AttributeRepository extends JpaRepository<Attribute, Integer>{
	 boolean existsByName(String name);
}
