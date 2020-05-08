package com.shopping.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IProductImageDAO;
import com.shopping.entity.ProductImage;
import com.shopping.repository.ProductImageRepository;

@Repository("productImageDAO")
@Transactional
public class ProductImageImpl implements IProductImageDAO{

	@Autowired
	private ProductImageRepository productImageRepository;
	
	
	@Override
	public void insertOrUpdate(ProductImage entity) {
		// TODO Auto-generated method stub
		productImageRepository.save(entity);
	}

	@Override
	public void delete(ProductImage entity) {
		// TODO Auto-generated method stub
		productImageRepository.delete(entity);
	}

	@Override
	public Page<ProductImage> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
