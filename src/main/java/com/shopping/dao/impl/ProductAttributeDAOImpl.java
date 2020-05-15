package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IProductAttributeDAO;
import com.shopping.entity.ProductAttribute;
import com.shopping.repository.ProductAttributeRepository;

@Repository("productAttributeDAO")
@Transactional
public class ProductAttributeDAOImpl implements IProductAttributeDAO {
	
	@Autowired
	private ProductAttributeRepository productAttributeRepository;

	@Override
	public void insertOrUpdate(ProductAttribute entity) {
		// TODO Auto-generated method stub
		productAttributeRepository.save(entity);
	}

	@Override
	public void delete(ProductAttribute entity) {
		// TODO Auto-generated method stub
		productAttributeRepository.delete(entity);
	}

	@Override
	public Page<ProductAttribute> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int productAttributeId) {
		// TODO Auto-generated method stub
		productAttributeRepository.deleteById(productAttributeId);
	}

	@Override
	public List<ProductAttribute> findByProductId(int productId) {
		// TODO Auto-generated method stub
		return productAttributeRepository.findByProductId(productId);
	}

	@Override
	public Optional<ProductAttribute> findById(int productAttributeId) {
		// TODO Auto-generated method stub
		return productAttributeRepository.findById(productAttributeId);
	}

	@Override
	public Boolean isValueExist(String value, int productId) {
		// TODO Auto-generated method stub
		return productAttributeRepository.existsByValueAndProductId(value, productId);
	}
	
}
