package com.shopping.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Product;
import com.shopping.entity.Promotion;
import com.shopping.service.IPromotionService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/promotions")
public class PromotionController {
	
	@Autowired
	private IPromotionService promotionService;
	
	@GetMapping
	public ResponseModel<PageModel<Promotion>> getPromotionPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String name, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, @RequestParam(required = false) String type) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);

		return promotionService.findAll(page, size, map);
	}
	
	@PostMapping
	public ResponseModel<Integer> addPromotion(@RequestBody Promotion promotion) {
		return promotionService.add(promotion);		
	}
	
	@GetMapping("/add-detail")
	public ResponseModel<Promotion> addPromotion(@RequestParam int promotionId, @RequestParam String type, @RequestParam int id) {
		return promotionService.addDetail(promotionId, type, id);		
	}
	
	@GetMapping("/{promotionId}")
	public ResponseModel<Promotion> getPromotionById(@PathVariable int promotionId) {
		return promotionService.getPromotionById(promotionId);		
	}
	
	@GetMapping("/{promotionId}/products")
	public ResponseModel<List<Product>> getProductByPromotionId(@PathVariable int promotionId) {
		return promotionService.getProductByPromotionId(promotionId);		
	}
}
