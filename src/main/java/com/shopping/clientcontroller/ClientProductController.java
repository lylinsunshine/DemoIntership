package com.shopping.clientcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
