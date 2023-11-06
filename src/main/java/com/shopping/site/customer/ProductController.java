package com.shopping.site.customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.dto.ProductDTO;
import com.shopping.entity.Product;
import com.shopping.entity.ProductAttribute;
import com.shopping.entity.ProductImage;
import com.shopping.service.IProductService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping
	public ResponseModel<PageModel<ProductDTO>> getProductPage(@RequestParam int page, @RequestParam int size,
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
	public ResponseModel<PageModel<Product>> getProductPageAll() {
		Product product = productService.findById(3).getData();
		String nameWithoutSpecialCharacter = product.getName().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String nameWithoutUnecesarySpace = nameWithoutSpecialCharacter.trim().replaceAll(" +", " ");
		String newName = nameWithoutUnecesarySpace.replace(' ', '-').toLowerCase();
		product.setName(newName);
		 System.out.println(product.getName());
		return productService.getAll();
	}
	
	@GetMapping("/checkname/{name}")
	public ResponseModel<Boolean> isNameExist(@PathVariable String name) {
		return productService.isNameExist(name);
	}
	
	@GetMapping("/checksku/{sku}")
	public ResponseModel<Boolean> isSkuExist(@PathVariable String sku) {
		return productService.isSkuExist(sku);
	}
	
	@GetMapping("/checkurl/{url}")
	public ResponseModel<Boolean> isUrlExist(@PathVariable String url) {
		return productService.isUrlExist(url);
	}
	
	@GetMapping("/checkvalue")
	public ResponseModel<Boolean> isValueExist(@RequestParam String value, @RequestParam int productId) {
		return productService.isValueExist(value, productId);
	}

	@GetMapping("/{productId}")
	public ResponseModel<Product> getOneProduct(@PathVariable int productId) {
		return productService.findById(productId);
	}
	
	@GetMapping("/string")
	public ResponseModel<Product> getOneProduct1() {
		 Product product = productService.findById(3).getData();
		 product.setName(product.getName().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		 System.out.println(product.getName());
		 return productService.findById(3);
	}

	@PostMapping
	public ResponseModel<Product> addProduct(@RequestBody Product product) {
		return productService.add(product);		
	}

	@PatchMapping
	public ResponseModel<Product> updateProduct(@RequestBody Product product) {
		return productService.update(product);
	}
	
	@PostMapping("/display-order")
	public ResponseModel<Product> updateImageDisplayOrder(@RequestParam int imageId1, @RequestParam int imageId2) {
		return productService.updateDisplayOrder(imageId1, imageId2);
	}
	
	@PostMapping("/delete-image")
	public ResponseModel<List<ProductImage>> deleteImage(@RequestParam int imageId) {
		return productService.deleteProductImage(imageId);
	}
	
	@PostMapping("/add-image")
	public ResponseModel<List<ProductImage>> deleteImage(@RequestBody ProductImage productImage) {
		return productService.addProductImage(productImage);
	}
	
	
	
	@PutMapping("/test")
	public ResponseModel<Product> updateProduct(@RequestParam String product, @RequestParam MultipartFile[] files) throws IOException {
		//ObjectMapper objectMapper = new ObjectMapper();
		//Product p = objectMapper.readValue(product, Product.class);
		return new ResponseModel<Product>(null, HttpStatus.ACCEPTED, "update ok");
	}
	
	@DeleteMapping("/{productId}")
	public ResponseModel<Product> deleteProduct(@PathVariable int productId) {
		return productService.deleteById(productId);
	}
	
	@DeleteMapping("/product-attribute/{productAttributeId}")
	public ResponseModel<List<ProductAttribute>> deleteProductAttribute(@PathVariable int productAttributeId) {
		return productService.deleteProductAttribute(productAttributeId);
	}
	
	@PostMapping("/product-attribute")
	public ResponseModel<List<ProductAttribute>> insertOrUpdateAtribute(@RequestBody ProductAttribute productAttribute) {
		return productService.insertOrUpdateAttribue(productAttribute);
	}
}
