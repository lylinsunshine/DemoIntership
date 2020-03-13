package com.shopping.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IPromotionDAO;
import com.shopping.entity.Promotion;
import com.shopping.repository.PromotionRepository;

@Repository("promotionDAO")
@Transactional
public class PromotionDAOImpl implements IPromotionDAO {
	
	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public void insertOrUpdate(Promotion entity) {
		// TODO Auto-generated method stub
		promotionRepository.save(entity);
	}

	@Override
	public void delete(Promotion entity) {
		// TODO Auto-generated method stub
		promotionRepository.delete(entity);
	}

	
}
