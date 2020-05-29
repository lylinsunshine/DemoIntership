package com.shopping.clientcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.ClientProductDTO;
import com.shopping.dto.ProductDTO;
import com.shopping.dto.ProductDetailDTO;
import com.shopping.entity.Product;
import com.shopping.service.IProductService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/products")
public class ClientProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping("/{productUrl}")
	public ResponseModel<ProductDetailDTO> getProductInfo(@PathVariable String productUrl) {
		return productService.getProductInfo(productUrl);
	}

	@GetMapping
	public ResponseModel<PageModel<ClientProductDTO>> getClientProductPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int priceFrom,
			@RequestParam(defaultValue = "0") int priceTo, @RequestParam(defaultValue = "0") int manufacturerId, 
			@RequestParam(defaultValue = "0") int categoryId, @RequestParam(defaultValue = "0") int sortBy) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("priceFrom", priceFrom);
		map.put("priceTo", priceTo);
		map.put("manufacturerId", manufacturerId);
		map.put("categoryId", categoryId);
		map.put("sortBy", sortBy);
		
//		System.out.println(sortBy);

		return productService.clientFindAll(page, size, map);
	}
}
