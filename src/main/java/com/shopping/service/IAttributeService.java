package com.shopping.service;

import java.util.List;

import com.shopping.entity.Attribute;
import com.shopping.util.ResponseModel;

public interface IAttributeService {
	
	List<Attribute> getAllAttributes();

	List<Attribute> addAttribute(Attribute attribute);
	
	ResponseModel<Boolean> isNameExist(String name);

}
