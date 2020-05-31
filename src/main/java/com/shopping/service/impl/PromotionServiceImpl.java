package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopping.dao.IPromotionDAO;
import com.shopping.dao.IPromotionProductDAO;
import com.shopping.entity.Product;
import com.shopping.entity.Promotion;
import com.shopping.entity.PromotionProduct;
import com.shopping.repository.ProductRepository;
import com.shopping.repository.PromotionRepository;
import com.shopping.service.IPromotionService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class PromotionServiceImpl implements IPromotionService{
	
	@Autowired
	private IPromotionDAO promotionDAO;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private IPromotionProductDAO promotionProductDAO;

	@Override
	public ResponseModel<PageModel<Promotion>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Promotion> page = promotionDAO.page(pageNumber, pageSize, map);
		PageModel<Promotion> pageModel = new PageModel<Promotion>(page.getContent(), pageNumber, page.getTotalPages());
		
		
		return new ResponseModel<PageModel<Promotion>>(pageModel, HttpStatus.OK, "All promotions");
	}

	@Override
	public ResponseModel<Integer> add(Promotion promotion) {
		// TODO Auto-generated method stub
		Promotion p = promotionRepository.save(promotion);		
		return new ResponseModel<Integer>(p.getId(), HttpStatus.OK, "Add Success");
	}

	@Override
	public ResponseModel<Promotion> addDetail(int promotionId, String type, int id) {
		// TODO Auto-generated method stub
		List<Product> pList = new ArrayList<Product>();
		if(type.equals("CATEGORY")) {
			 pList = productRepository.findByCategoryId(id);
		} else {
			 pList = productRepository.findByManufacturerId(id);
		}
		
		Promotion p = new Promotion(promotionId, "", null, null, "", "", "", 0,"");
		
		pList.forEach(
				item -> promotionProductDAO.insertOrUpdate(new PromotionProduct(0, 0, 0, p, item))
				);
		return new ResponseModel<Promotion>(null, HttpStatus.OK, "Add Success");
	}

	@Override
	public ResponseModel<Promotion> getPromotionById(int promotionId) {
		// TODO Auto-generated method stub
		Promotion p = promotionDAO.getPromotionById(promotionId);
		return new ResponseModel<Promotion>(p, HttpStatus.OK, "Get Promotion Success");
	}

	@Override
	public ResponseModel<List<Product>> getProductByPromotionId(int promotionId) {
		// TODO Auto-generated method stub
		List<Product> list = productRepository.findByPromotionId(promotionId);
		return new ResponseModel<List<Product>>(list, HttpStatus.OK, "Get Product Success");
	}
	
	
	
}
