package com.shopping.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.shopping.dao.IProductDAO;
import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements IProductDAO {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void insertOrUpdate(Product entity) {
		// TODO Auto-generated method stub
		productRepository.save(entity);

	}

	@Override
	public void delete(Product entity) {
		// TODO Auto-generated method stub
		productRepository.delete(entity);

	}

	@Override
	public List<String> getAllProductName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> page(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
