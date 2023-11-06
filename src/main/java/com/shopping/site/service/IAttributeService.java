package com.shopping.site.service;

import java.util.List;

import com.shopping.site.entity.Attribute;
import com.shopping.site.util.ResponseModel;

public interface IAttributeService {
	
	List<Attribute> getAllAttributes();

	List<Attribute> addAttribute(Attribute attribute);
	
	ResponseModel<Boolean> isNameExist(String name);

}
