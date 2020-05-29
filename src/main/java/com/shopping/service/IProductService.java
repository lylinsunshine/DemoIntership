package com.shopping.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.shopping.dto.ClientProductDTO;
import com.shopping.dto.ProductDTO;
import com.shopping.dto.ProductDetailDTO;
import com.shopping.entity.Product;
import com.shopping.entity.ProductAttribute;
import com.shopping.entity.ProductImage;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IProductService {
	
	ResponseModel<PageModel<ProductDTO>> findAll(int pageNumber, int pageSize, Map<String, Object> map);
	
	ResponseModel<PageModel<ClientProductDTO>> clientFindAll(int pageNumber, int pageSize, Map<String, Object> map);
	
	ResponseModel<Product> findById(int productId);
	
	ResponseModel<PageModel<Product>> getAll();
	
	String storeFile(MultipartFile file) throws IOException;
	
	ResponseModel<Product> add(Product Product);
	
	ResponseModel<Product> deleteById(int manufacturerId);
	
	ResponseModel<Product> update(Product Product);
	
	ResponseModel<Boolean> isNameExist(String name);
	
	ResponseModel<Boolean> isSkuExist(String sku);
	
	ResponseModel<Boolean> isUrlExist(String url);
	
	ResponseModel<Boolean> isValueExist(String value, int productId);
	
	ResponseModel<Product> updateDisplayOrder(int imageId1, int imageId2);
	
	ResponseModel<List<ProductImage>> deleteProductImage(int imageId);
	
	ResponseModel<List<ProductImage>> addProductImage(ProductImage productImage);
	
	ResponseModel<List<ProductAttribute>> deleteProductAttribute(int productAttributeId);
	
	ResponseModel<List<ProductAttribute>> insertOrUpdateAttribue(ProductAttribute productAttribute);
	
	ResponseModel<ProductDetailDTO> getProductInfo(String url);
	

}
