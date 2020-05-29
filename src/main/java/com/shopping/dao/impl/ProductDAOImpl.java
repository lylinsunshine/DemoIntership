package com.shopping.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.shopping.dao.IProductDAO;
import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;
import com.shopping.specification.ProductSpec;

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
	public Page<Product> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String name = (String) map.get("name");
		int priceFrom = (int) map.get("priceFrom");
		int priceTo = (int) map.get("priceTo");
		int manufacturerId = (int) map.get("manufacturerId");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Product> spec = ProductSpec.search(name, priceFrom, priceTo, manufacturerId);
		return productRepository.findAll(spec, pageable);
	}

	@Override
	public Optional<Product> findById(int productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	@Override
	public void deleteById(int productId) {
		// TODO Auto-generated method stub
		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll(Specification.where(ProductSpec.hasManufacturer("Thang1")));
	}

	@Override
	public boolean isNameExist(String name) {
		// TODO Auto-generated method stub
		return productRepository.existsByName(name);
	}

	@Override
	public boolean isSkuExist(String sku) {
		// TODO Auto-generated method stub
		return productRepository.existsBySku(sku);
	}

	@Override
	public boolean isUrlExist(String url) {
		// TODO Auto-generated method stub
		return productRepository.existsByUrl(url);
	}

	@Override
	public Product findByUrl(String url) {
		// TODO Auto-generated method stub
		return productRepository.findByUrl(url);
	}

	@Override
	public Page<Product> clientPage(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String name = (String) map.get("name");
		int priceFrom = (int) map.get("priceFrom");
		int priceTo = (int) map.get("priceTo");
		int manufacturerId = (int) map.get("manufacturerId");
		int categoryId = (int) map.get("categoryId");
		int sortBy = (int) map.get("sortBy");
		
		if(sortBy ==0) {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
			Specification<Product> spec = ProductSpec.clientSearch(name, priceFrom, priceTo, manufacturerId, categoryId);
			return productRepository.findAll(spec, pageable);
		} else {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
			Specification<Product> spec = ProductSpec.clientSearch(name, priceFrom, priceTo, manufacturerId, categoryId);
			return productRepository.findAll(spec, pageable);
		}
	}

	@Override
	public List<Product> findAllByParentCategory(int categoryId) {
		// TODO Auto-generated method stub
		return productRepository.findAllByParentCategory(categoryId);
	}

}
