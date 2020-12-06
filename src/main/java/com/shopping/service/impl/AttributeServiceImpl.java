package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IAttributeDAO;
import com.shopping.entity.Attribute;
import com.shopping.repository.AttributeRepository;
import com.shopping.service.IAttributeService;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class AttributeServiceImpl implements IAttributeService {
	
	@Autowired
	private IAttributeDAO attributeDAO;
	
	@Autowired
	private AttributeRepository attributeRepository;

	@Override
	public List<Attribute> getAllAttributes() { 
		return attributeDAO.getAllAttributes();
	}

	@Override
	public List<Attribute> addAttribute(Attribute attribute) {
		attributeDAO.insertOrUpdate(attribute);		
		return attributeDAO.getAllAttributes();
	}

	@Override
	public ResponseModel<Boolean> isNameExist(String name) {
		return new ResponseModel<Boolean>(attributeRepository.existsByName(name), HttpStatus.OK, "isNameExist");
	}

}
