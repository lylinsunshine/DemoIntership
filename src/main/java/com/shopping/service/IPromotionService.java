package com.shopping.service;

import java.util.List;
import java.util.Map;

import com.shopping.entity.Product;
import com.shopping.entity.Promotion;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IPromotionService {
	
	ResponseModel<PageModel<Promotion>> findAll(int pageNumber, int pageSize, Map<String, Object> map);
	
	ResponseModel<Integer> add(Promotion promotion);
	
	ResponseModel<Promotion> addDetail(int promotionId, String type, int id);
	
	ResponseModel<Promotion> getPromotionById(int promotionId);
	
	ResponseModel<List<Product>> getProductByPromotionId(int promotionId);
}
