package com.shopping.dao.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IPromotionDAO;
import com.shopping.entity.Promotion;
import com.shopping.repository.PromotionRepository;
import com.shopping.specification.PromotionSpec;

@Repository("promotionDAO")
@Transactional
public class PromotionDAOImpl implements IPromotionDAO {
	
	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public void insertOrUpdate(Promotion entity) {
		promotionRepository.save(entity);
	}

	@Override
	public void delete(Promotion entity) {
		promotionRepository.delete(entity);
		
	}

	@Override
	public Page<Promotion> page(int pageNumber, int pageSize, Map<String, Object> map) {
		String name = (String) map.get("name");
		Date startDate = (Date) map.get("startDate");
		Date endDate = (Date) map.get("endDate");
		String type = (String) map.get("type");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "endDate"));
		Specification<Promotion> spec = PromotionSpec.search(name, startDate, endDate, type);
		return promotionRepository.findAll(spec, pageable);
	}

	@Override
	public Promotion getPromotionById(int id) {
		return promotionRepository.findById(id).get();
	}

	
}
