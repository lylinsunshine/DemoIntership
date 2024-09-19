package com.shopping.site.staff;

import com.shopping.site.dto.ClientCategoryPageDTO;
import com.shopping.site.dto.ClientProductDTO;
import com.shopping.site.dto.ProductDetailDTO;
import com.shopping.site.service.CategoryService;
import com.shopping.site.service.ProductService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/clients/products")
@RequiredArgsConstructor
public class ClientProductController {

	private final ProductService productService;

	private final CategoryService categoryService;
	
	@GetMapping("/{productUrl}")
	public Response<ProductDetailDTO> getProductInfo(@PathVariable String productUrl) {
		return productService.getProductInfo(productUrl);
	}

	@GetMapping
	public Response<PageResponse<ClientProductDTO>> getClientProductPage(@RequestParam int page, @RequestParam int size,
																			  @RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int priceFrom,
																			  @RequestParam(defaultValue = "0") int priceTo, @RequestParam(defaultValue = "0") int manufacturerId,
																			  @RequestParam(defaultValue = "0") int categoryId, @RequestParam(defaultValue = "0") int sortBy, @RequestParam int initCategoryId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("priceFrom", priceFrom);
		map.put("priceTo", priceTo);
		map.put("manufacturerId", manufacturerId);
		map.put("categoryId", categoryId);
		map.put("sortBy", sortBy);

		return productService.clientFindByCategoryId(page, size, map, initCategoryId);
	}
	
	@GetMapping("/search")
	public Response<PageResponse<ClientProductDTO>> getClientProductPage(@RequestParam int page, @RequestParam int size,
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

		return productService.clientFindAll(page, size, map);
	}
	
	@GetMapping("/category/{categoryUrl}/manufacturer")
	public Response<ClientCategoryPageDTO> clientCategoryPage(@PathVariable String categoryUrl){
		return categoryService.clientAllManufacturerBelongCategory(categoryUrl);
	}
	
//	@GetMapping("/category/manufacturer")
//	public ResponseModel<ClientCategoryPageDTO> clientSearchPage(){
//		return categoryService.clientAllCategoriesAndManufacturer();
//	}
	
	@GetMapping("/related-product/{productId}")
	public Response<List<ClientProductDTO>> getRelatedProduct(@PathVariable int productId){
		return productService.getRelatedProduct(productId);
	}
	
}
