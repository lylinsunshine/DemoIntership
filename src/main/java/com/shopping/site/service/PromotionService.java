package com.shopping.site.service;

import com.shopping.site.entity.Product;
import com.shopping.site.entity.Promotion;
import com.shopping.site.entity.PromotionProduct;
import com.shopping.site.repository.ProductRepository;
import com.shopping.site.repository.PromotionProductRepository;
import com.shopping.site.repository.PromotionRepository;
import com.shopping.site.specification.PromotionSpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class PromotionService {

	private final PromotionRepository promotionRepository;

	private final PromotionProductRepository promotionProductRepository;

	private final ProductRepository productRepository;

	public Response<PageResponse<Promotion>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		String name = (String) map.get("name");
		Date startDate = (Date) map.get("startDate");
		Date endDate = (Date) map.get("endDate");
		String type = (String) map.get("type");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "endDate"));
		Specification<Promotion> spec = PromotionSpec.search(name, startDate, endDate, type);

		Page<Promotion> page = promotionRepository.findAll(spec, pageable);
		PageResponse<Promotion> pageModel = new PageResponse<Promotion>(page.getContent(), pageNumber, page.getTotalPages());

		return new Response<PageResponse<Promotion>>(pageModel, 200, "All promotions");
	}

	public Response<Integer> add(Promotion promotion) {
		Promotion p = promotionRepository.save(promotion);
		return new Response<Integer>(p.getId(), 200, "Add Success");
	}

	public Response<Promotion> addDetail(int promotionId, String type, int id) {
		List<Product> pList = new ArrayList<Product>();
		if (type.equals("CATEGORY")) {
			pList = productRepository.findByCategoryId(id);
		} else {
			pList = productRepository.findByManufacturerId(id);
		}

		Promotion p = new Promotion(promotionId, "", null, null, "", "", "", 0, "");

		pList.forEach(item -> promotionProductRepository.save(new PromotionProduct(0, 0, 0, p, item)));
		return new Response<Promotion>(null, 200, "Add Success");
	}

	public Response<Promotion> getPromotionById(int promotionId) {
		Promotion p = promotionRepository.findById(promotionId).get();
		return new Response<Promotion>(p, 200, "Get Promotion Success");
	}

	public Response<List<Product>> getProductByPromotionId(int promotionId) {
		List<Product> list = productRepository.findByPromotionId(promotionId);
		return new Response<List<Product>>(list,200, "Get Product Success");
	}

}
