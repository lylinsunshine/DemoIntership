package com.shopping.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IPromotionProductDAO;
import com.shopping.entity.PromotionProduct;
import com.shopping.repository.PromotionProductRepository;

@Repository("promotionProductDAO")
@Transactional
public class PromotionProductDAOImpl implements IPromotionProductDAO {
	
	@Autowired
	private PromotionProductRepository promotionProductRepository;

	@Override
	public void insertOrUpdate(PromotionProduct entity) {
		promotionProductRepository.save(entity);
	}

	@Override
	public void delete(PromotionProduct entity) {
		promotionProductRepository.delete(entity);
	}

	@Override
	public Page<PromotionProduct> page(int pageNumber, int pageSize, Map<String, Object> map) {
		return null;
	}

}
