package com.shopping.site.repository;

import com.shopping.site.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("attributeRepository")
public interface AttributeRepository extends JpaRepository<Attribute, Integer>{
	 boolean existsByName(String name);


}
