package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IAttributeDAO;
import com.shopping.entity.Attribute;
import com.shopping.service.IAttributeService;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class AttributeServiceImpl implements IAttributeService {
	
	@Autowired
	private IAttributeDAO attributeDAO;

	@Override
	public List<Attribute> getAllAttributes() {
		// TODO Auto-generated method stub 
		return attributeDAO.getAllAttributes();
	}

}
