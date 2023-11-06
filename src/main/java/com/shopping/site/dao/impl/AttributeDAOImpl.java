package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IAttributeDAO;
import com.shopping.entity.Attribute;
import com.shopping.repository.AttributeRepository;

@Repository("attributeDAO")
@Transactional
public class AttributeDAOImpl implements IAttributeDAO {

	@Autowired
	private AttributeRepository attributeRepository;
	@Override
	public void insertOrUpdate(Attribute entity) {
		attributeRepository.save(entity); 
	}

	@Override
	public void delete(Attribute entity) {
		attributeRepository.delete(entity);
	}

	@Override
	public Page<Attribute> page(int pageNumber, int pageSize, Map<String, Object> map) {
		return null;
	}

	@Override
	public List<Attribute> getAllAttributes() {
		return attributeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public Optional<Attribute> findById(int id) {
		return attributeRepository.findById(id);
	}

}
