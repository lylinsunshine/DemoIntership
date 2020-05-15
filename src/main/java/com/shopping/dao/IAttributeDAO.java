package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Attribute;

public interface IAttributeDAO extends GenericDAO<Attribute, Integer> {
	
	List<Attribute> getAllAttributes();
}
