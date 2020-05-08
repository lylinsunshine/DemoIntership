package com.shopping.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.dao.IProductDAO;
import com.shopping.dao.IProductImageDAO;
import com.shopping.dto.ProductDTO;
import com.shopping.entity.Manufacturer;
import com.shopping.entity.Product;
import com.shopping.entity.ProductImage;
import com.shopping.service.IProductService;
import com.shopping.util.Constants;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private IProductImageDAO productImageDAO;

	@Override
	public ResponseModel<Product> findById(int productId) {
		// TODO Auto-generated method stub
		Optional<Product> product = productDAO.findById(productId);
		if (product.isPresent())
			return new ResponseModel<Product>(product.get(), HttpStatus.OK, "Get OK");
		else
			return new ResponseModel<Product>(null, HttpStatus.NOT_FOUND, "Get Fail");
	}

	@Override
	public ResponseModel<Product> add(Product product) {
		// TODO Auto-generated method stub			
		String nameWithoutSpecialCharacter = product.getName().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String nameWithoutUnecesarySpace = nameWithoutSpecialCharacter.trim().replaceAll(" +", " ");
		String url = nameWithoutUnecesarySpace.replace(' ', '-').toLowerCase();
		product.setUrl(url);
		productDAO.insertOrUpdate(product);
		
		if(product.getImage()!=null) {
			ProductImage image = new ProductImage(0, product.getImage(), 1, product);
			productImageDAO.insertOrUpdate(image);
		}
			
		return new ResponseModel<Product>(null, HttpStatus.OK, "Insert Product Succesful");
	}

	@Override
	public ResponseModel<Product> update(Product product) {
		// TODO Auto-generated method stub
//		String name = null;
//		for (MultipartFile multipartFile : file) {
//			name = storeFile(multipartFile);
//		}
//		product.setImage(name);
		productDAO.insertOrUpdate(product);
		return new ResponseModel<Product>(null, HttpStatus.OK, "Update Product Succesful");
	}

	@Override
	public ResponseModel<PageModel<ProductDTO>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Product> page = productDAO.page(pageNumber, pageSize, map);
		PageModel<Product> pageModel = new PageModel<Product>(page.getContent(), pageNumber, page.getTotalPages());
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDTO> list = Arrays.asList(modelMapper.map(page.getContent(), ProductDTO[].class));
		PageModel<ProductDTO> pagemodel2 = new PageModel<ProductDTO>(list, pageNumber, page.getTotalPages());
		
		
		return new ResponseModel<PageModel<ProductDTO>>(pagemodel2, HttpStatus.OK, "All products");
	}

	@Override
	public ResponseModel<PageModel<Product>> getAll() {
		// TODO Auto-generated method stub
		PageModel<Product> pageModel = new PageModel<Product>(productDAO.getAllProduct(), 1, 0);
		return new ResponseModel<PageModel<Product>>(pageModel, HttpStatus.OK, "All products");
	}
	
	private final Path rootLocation = Paths.get("C://Users//Blindfold Gang//Desktop//images/");

	@Override
	public String storeFile(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String fileName = UUID.randomUUID().toString() +"."+ extension;
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
			return fileName;
	}

	@Override
	public ResponseModel<Product> deleteById(int manufacturerId) {
		// TODO Auto-generated method stub
		productDAO.deleteById(manufacturerId);
		return new ResponseModel<Product>(null, HttpStatus.OK, "Delete Product Successful");
	}

}
