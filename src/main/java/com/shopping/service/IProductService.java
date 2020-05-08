package com.shopping.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.shopping.dto.ProductDTO;
import com.shopping.entity.Product;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IProductService {
	
	ResponseModel<PageModel<ProductDTO>> findAll(int pageNumber, int pageSize, Map<String, Object> map);
	
	ResponseModel<Product> findById(int productId);
	
	ResponseModel<PageModel<Product>> getAll();
	
	String storeFile(MultipartFile file) throws IOException;
	
	ResponseModel<Product> add(Product Product);
	
	ResponseModel<Product> deleteById(int manufacturerId);
	
	ResponseModel<Product> update(Product Product);
	
	//ResponseModel<Product> update(Product Product, MultipartFile[] file) throws IOException;

}
