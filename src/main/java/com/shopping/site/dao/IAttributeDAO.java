package com.shopping.dao;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Attribute;

public interface IAttributeDAO extends GenericDAO<Attribute, Integer> {
	
	List<Attribute> getAllAttributes();
	
	Optional<Attribute> findById(int id);
}
