package com.shopping.site.customer;

import com.shopping.site.dto.ProductDTO;
import com.shopping.site.entity.Product;
import com.shopping.site.entity.ProductAttribute;
import com.shopping.site.entity.ProductImage;
import com.shopping.site.service.ProductService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public Response<PageResponse<ProductDTO>> getProductPage(@RequestParam int page, @RequestParam int size,
															 @RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int priceFrom,
															 @RequestParam(defaultValue = "0") int priceTo, @RequestParam(defaultValue = "0") int manufacturerId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("priceFrom", priceFrom);
		map.put("priceTo", priceTo);
		map.put("manufacturerId", manufacturerId);

		return productService.findAll(page, size, map);
	}

	@GetMapping("/hello")
	public Response<PageResponse<Product>> getProductPageAll() {
		Product product = productService.findById(3).getData();
		String nameWithoutSpecialCharacter = product.getName().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String nameWithoutUnecesarySpace = nameWithoutSpecialCharacter.trim().replaceAll(" +", " ");
		String newName = nameWithoutUnecesarySpace.replace(' ', '-').toLowerCase();
		product.setName(newName);
		return productService.getAll();
	}
	
	@GetMapping("/checkname/{name}")
	public Response<Boolean> isNameExist(@PathVariable String name) {
		return productService.isNameExist(name);
	}
	
	@GetMapping("/checksku/{sku}")
	public Response<Boolean> isSkuExist(@PathVariable String sku) {
		return productService.isSkuExist(sku);
	}
	
	@GetMapping("/checkurl/{url}")
	public Response<Boolean> isUrlExist(@PathVariable String url) {
		return productService.isUrlExist(url);
	}
	
	@GetMapping("/checkvalue")
	public Response<Boolean> isValueExist(@RequestParam String value, @RequestParam int productId) {
		return productService.isValueExist(value, productId);
	}

	@GetMapping("/{productId}")
	public Response<Product> getOneProduct(@PathVariable int productId) {
		return productService.findById(productId);
	}
	
	@GetMapping("/string")
	public Response<Product> getOneProduct1() {
		 Product product = productService.findById(3).getData();
		 product.setName(product.getName().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		 System.out.println(product.getName());
		 return productService.findById(3);
	}

	@PostMapping
	public Response<Product> addProduct(@RequestBody Product product) {
		return productService.add(product);		
	}

	@PatchMapping
	public Response<Product> updateProduct(@RequestBody Product product) {
		return productService.update(product);
	}
	
	@PostMapping("/display-order")
	public Response<Product> updateImageDisplayOrder(@RequestParam int imageId1, @RequestParam int imageId2) {
		return productService.updateDisplayOrder(imageId1, imageId2);
	}
	
	@PostMapping("/delete-image")
	public Response<List<ProductImage>> deleteImage(@RequestParam int imageId) {
		return productService.deleteProductImage(imageId);
	}
	
	@PostMapping("/add-image")
	public Response<List<ProductImage>> deleteImage(@RequestBody ProductImage productImage) {
		return productService.addProductImage(productImage);
	}
	
	
	
	@PutMapping("/test")
	public Response<Product> updateProduct(@RequestParam String product, @RequestParam MultipartFile[] files) throws IOException {
		//ObjectMapper objectMapper = new ObjectMapper();
		//Product p = objectMapper.readValue(product, Product.class);
		return new Response<Product>(null, 200, "update ok");
	}
	
	@DeleteMapping("/{productId}")
	public Response<Product> deleteProduct(@PathVariable int productId) {
		return productService.deleteById(productId);
	}
	
	@DeleteMapping("/product-attribute/{productAttributeId}")
	public Response<List<ProductAttribute>> deleteProductAttribute(@PathVariable int productAttributeId) {
		return productService.deleteProductAttribute(productAttributeId);
	}
	
	@PostMapping("/product-attribute")
	public Response<List<ProductAttribute>> insertOrUpdateAtribute(@RequestBody ProductAttribute productAttribute) {
		return productService.insertOrUpdateAttribue(productAttribute);
	}
}
