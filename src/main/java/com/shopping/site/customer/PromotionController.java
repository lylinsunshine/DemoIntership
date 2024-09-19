package com.shopping.site.customer;

import com.shopping.site.entity.Product;
import com.shopping.site.entity.Promotion;
import com.shopping.site.service.PromotionService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController {

	private final PromotionService promotionService;
	
	@GetMapping
	public Response<PageResponse<Promotion>> getPromotionPage(@RequestParam int page, @RequestParam int size,
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
	public Response<Integer> addPromotion(@RequestBody Promotion promotion) {
		return promotionService.add(promotion);		
	}
	
	@GetMapping("/add-detail")
	public Response<Promotion> addPromotion(@RequestParam int promotionId, @RequestParam String type, @RequestParam int id) {
		return promotionService.addDetail(promotionId, type, id);		
	}
	
	@GetMapping("/{promotionId}")
	public Response<Promotion> getPromotionById(@PathVariable int promotionId) {
		return promotionService.getPromotionById(promotionId);		
	}
	
	@GetMapping("/{promotionId}/products")
	public Response<List<Product>> getProductByPromotionId(@PathVariable int promotionId) {
		return promotionService.getProductByPromotionId(promotionId);		
	}
}
