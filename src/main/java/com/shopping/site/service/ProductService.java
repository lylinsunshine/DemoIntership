package com.shopping.site.service;

import com.shopping.site.dto.ClientProductDTO;
import com.shopping.site.dto.ProductDTO;
import com.shopping.site.dto.ProductDetailDTO;
import com.shopping.site.entity.*;
import com.shopping.site.repository.*;
import com.shopping.site.specification.ProductSpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	private final ProductImageRepository productImageRepository;

	private final ProductAttributeRepository productAttributeRepository;

	private final AttributeRepository attributeRepository;

	private final PromotionRepository promotionRepository;
	private ModelMapper modelMapper;

	public Response<Product> findById(int productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent())
			return new Response<Product>(product.get(), 200, "Get OK");
		else
			return new Response<Product>(null, 404, "Get Fail");
	}

	public Response<Product> add(Product product) {
		String nameWithoutSpecialCharacter = product.getName().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String nameWithoutUnecesarySpace = nameWithoutSpecialCharacter.trim().replaceAll(" +", " ");
		String url = nameWithoutUnecesarySpace.replace(' ', '-').toLowerCase();
		product.setUrl(url);
		productRepository.save(product);

		if (product.getImage() != null) {
			ProductImage image = new ProductImage(0, product.getImage(), 1, product);
			productImageRepository.save(image);
		}

		return new Response<>(null, 200, "Insert Product Succesful");
	}

	public Response<Product> update(Product product) {
		ModelMapper modelMapper2 = new ModelMapper();
		modelMapper2.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

		Product p = productRepository.findById(product.getId()).get();
		modelMapper2.map(product, p);

		// productDAO.insertOrUpdate(p);
		return new Response<Product>(null, 200, "Update Product Succesful");
	}

	public Response<PageResponse<ProductDTO>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		String name = (String) map.get("name");
		int priceFrom = (int) map.get("priceFrom");
		int priceTo = (int) map.get("priceTo");
		int manufacturerId = (int) map.get("manufacturerId");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Product> spec = ProductSpec.search(name, priceFrom, priceTo, manufacturerId);

		Page<Product> page = productRepository.findAll(spec, pageable);
		//PageModel<Product> pageModel = new PageModel<Product>(page.getContent(), pageNumber, page.getTotalPages());
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDTO> list = Arrays.asList(modelMapper.map(page.getContent(), ProductDTO[].class));
		PageResponse<ProductDTO> pagemodel2 = new PageResponse<ProductDTO>(list, pageNumber, page.getTotalPages());

		return new Response<PageResponse<ProductDTO>>(pagemodel2, 200, "All products");
	}

	public Response<PageResponse<Product>> getAll() {
		PageResponse<Product> pageModel = new PageResponse<Product>(productRepository.findAll(Specification.where(ProductSpec.hasManufacturer("Thang1"))), 1, 0);
		return new Response<PageResponse<Product>>(pageModel, 200, "All products");
	}

	private final Path rootLocation = Paths.get("C://Users//Blindfold Gang//Desktop//images/");

	public String storeFile(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString() + "." + extension;
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
		return fileName;
	}

	public Response<Product> deleteById(int manufacturerId) {
		productRepository.deleteById(manufacturerId);
		return new Response<Product>(null, 200, "Delete Product Successful");
	}

	public Response<Boolean> isNameExist(String name) {
		boolean isNameExist = productRepository.existsByName(name);
		return new Response<Boolean>(isNameExist, 200, "isNameExist");
	}

	public Response<Boolean> isSkuExist(String sku) {
		boolean isSkuExist = productRepository.existsBySku(sku);
		return new Response<Boolean>(isSkuExist, 200, "isSkuExist");
	}

	public Response<Boolean> isUrlExist(String url) {
		boolean isUrlExist = productRepository.existsByUrl(url);
		return new Response<Boolean>(isUrlExist, 200, "isUrlExist");
	}

	public Response<Product> updateDisplayOrder(int imageId1, int imageId2) {
		ProductImage image1 = productImageRepository.findById(imageId1).get();
		ProductImage image2 = productImageRepository.findById(imageId2).get();

		int temp1 = image1.getDisplayOrder();
		int temp2 = image2.getDisplayOrder();
		image1.setDisplayOrder(temp2);
		image2.setDisplayOrder(temp1);

		return new Response<Product>(null, 200, "Update Display Order");
	}

	public Response<List<ProductImage>> deleteProductImage(int imageId) {
		ProductImage image = productImageRepository.findById(imageId).get();
		int productId = image.getProductEntity().getId();
		int displayOrder = image.getDisplayOrder();
		productImageRepository.delete(image);

		List<ProductImage> listNeedRearrange = productImageRepository.findListAfterDelete(displayOrder, productId);
		if (listNeedRearrange.size() != 0) {
			for (ProductImage productImage : listNeedRearrange) {
				productImage.setDisplayOrder(productImage.getDisplayOrder() - 1);
			}
		}

		List<ProductImage> listImage = productImageRepository.findByProductId(productId);

		return new Response<List<ProductImage>>(listImage, 200, "Delete Success");
	}

	public Response<List<ProductImage>> addProductImage(ProductImage productImage) {
		productImageRepository.save(productImage);
		List<ProductImage> listImage = productImageRepository.findByProductId(productImage.getProductEntity().getId());
		return new Response<List<ProductImage>>(listImage, 200, "Add Image Success");
	}

	public Response<List<ProductAttribute>> deleteProductAttribute(int productAttributeId) {
		int productId = productAttributeRepository.findById(productAttributeId).get().getProductEntity().getId();
		productAttributeRepository.deleteById(productAttributeId);
		List<ProductAttribute> list = productAttributeRepository.findByProductId(productId);
		return new Response<List<ProductAttribute>>(list, 200, "Delete Success");
	}

	public Response<Boolean> isValueExist(String value, int productId) {
		boolean isValueExist = productAttributeRepository.existsByValueAndProductId(value, productId);
		return new Response<Boolean>(isValueExist, 200, "isValueExist");
	}

	public Response<List<ProductAttribute>> insertOrUpdateAttribue(ProductAttribute productAttribute) {
		ModelMapper modelMapper2 = new ModelMapper();
		modelMapper2.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

		if (productAttribute.getId() == 0) {
			productAttributeRepository.save(productAttribute);
		} else {
			ProductAttribute p = productAttributeRepository.findById(productAttribute.getId()).get();
			modelMapper2.map(productAttribute, p);
		}

		List<ProductAttribute> list = productAttributeRepository.findByProductId(productAttribute.getProductEntity().getId());
		return new Response<List<ProductAttribute>>(list, 200, "Update Success");

	}

	public Response<ProductDetailDTO> getProductInfo(String url) {
		Product product = productRepository.findByUrl(url);
		ProductDetailDTO productDTO = modelMapper.map(product, ProductDetailDTO.class);
		if (productDTO.getProductAttributeSet() != null) {
			productDTO.getProductAttributeSet().removeIf(productAttribute -> productAttribute.getStatus() == false);

			List<Attribute> attributes = new ArrayList<Attribute>();
			for (ProductAttribute productAttribute : productDTO.getProductAttributeSet()) {
				Attribute attribute = attributeRepository.findById(productAttribute.getAttributeId()).get();
				if (!attributes.contains(attribute))
					attributes.add(attribute);
			}
			productDTO.setAttributeList(attributes);
		}
		if (productDTO.getProductImageSet() != null) {
			productDTO.getProductImageSet().removeIf(productImage -> productImage.getDisplayOrder() > 4);
		}

		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product productList : l2) {
			if (productDTO.getId() == productList.getId()) {
				if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
					Double temp = productDTO.getPrice() - Double.parseDouble(p.getDiscountNumber());
					if (temp >= 0) {
						productDTO.setPrice(temp);
					} else {
						productDTO.setPrice(0.0);
					}
				}
				if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
					Double temp = productDTO.getPrice()
							- Math.ceil(productDTO.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
					productDTO.setPrice(temp);
				}
			}

		}
		return new Response<>(productDTO, 200, "Get Success");
	}

	public Response<PageResponse<ClientProductDTO>> clientFindAll(int pageNumber, int pageSize,
			Map<String, Object> map) {

		Page<Product> page;

		String name = (String) map.get("name");
		int priceFrom = (int) map.get("priceFrom");
		int priceTo = (int) map.get("priceTo");
		int manufacturerId = (int) map.get("manufacturerId");
		int categoryId = (int) map.get("categoryId");
		int sortBy = (int) map.get("sortBy");

		if(sortBy ==0) {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
			Specification<Product> spec = ProductSpec.clientSearchPage(name, priceFrom, priceTo, manufacturerId, categoryId);
			page = productRepository.findAll(spec, pageable);
		} else {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
			Specification<Product> spec = ProductSpec.clientSearchPage(name, priceFrom, priceTo, manufacturerId, categoryId);
			page = productRepository.findAll(spec, pageable);
		}

		//PageModel<Product> pageModel = new PageModel<Product>(page.getContent(), pageNumber, page.getTotalPages());
		ModelMapper modelMapper = new ModelMapper();
		List<ClientProductDTO> list = Arrays.asList(modelMapper.map(page.getContent(), ClientProductDTO[].class));

		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product product : l2) {
			for (ClientProductDTO item : list) {
				if (item.getId() == product.getId()) {
					if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
						Double temp = item.getPrice() - Double.parseDouble(p.getDiscountNumber());
						if (temp >= 0) {
							item.setNewPrice(temp);
						}
					}
					if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
						Double temp = item.getPrice()
								- Math.ceil(item.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
						item.setNewPrice(temp);
					}
				}
			}
		}
		PageResponse<ClientProductDTO> pagemodel2 = new PageResponse<>(list, pageNumber,
				page.getTotalPages());

		return new Response<PageResponse<ClientProductDTO>>(pagemodel2, 200, "All products");
	}

	public Response<PageResponse<ClientProductDTO>> clientFindByCategoryId(int pageNumber, int pageSize,
			Map<String, Object> map, int initCategoryId) {
		Page<Product> page;

		String name = (String) map.get("name");
		int priceFrom = (int) map.get("priceFrom");
		int priceTo = (int) map.get("priceTo");
		int manufacturerId = (int) map.get("manufacturerId");
		int categoryId = (int) map.get("categoryId");
		int sortBy = (int) map.get("sortBy");

		if(sortBy ==0) {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
			Specification<Product> spec = ProductSpec.clientSearch(name, priceFrom, priceTo, manufacturerId, categoryId, initCategoryId);
			page = productRepository.findAll(spec, pageable);
		} else {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
			Specification<Product> spec = ProductSpec.clientSearch(name, priceFrom, priceTo, manufacturerId, categoryId, initCategoryId);
			page = productRepository.findAll(spec, pageable);
		}
		List<Product> l = new ArrayList<Product>(page.getContent());

		ModelMapper modelMapper = new ModelMapper();
		List<ClientProductDTO> list = Arrays.asList(modelMapper.map(l, ClientProductDTO[].class));

		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product product : l2) {
			for (ClientProductDTO item : list) {
				if (item.getId() == product.getId()) {
					if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
						Double temp = item.getPrice() - Double.parseDouble(p.getDiscountNumber());
						if (temp >= 0) {
							item.setNewPrice(temp);
						}
					}
					if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
						Double temp = item.getPrice()
								- Math.ceil(item.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
						item.setNewPrice(temp);
					}
				}
			}
		}
		PageResponse<ClientProductDTO> pagemodel2 = new PageResponse<ClientProductDTO>(list, pageNumber,
				page.getTotalPages());

		return new Response<PageResponse<ClientProductDTO>>(pagemodel2, 200, "All products");
	}

	public Response<List<ClientProductDTO>> getRelatedProduct(int productId) {
		List<Product> l = productRepository.getRelatedProduct(productId);
		List<ClientProductDTO> list = Arrays.asList(modelMapper.map(l, ClientProductDTO[].class));
		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product product : l2) {
			for (ClientProductDTO item : list) {
				if (item.getId() == product.getId()) {
					if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
						Double temp = item.getPrice() - Double.parseDouble(p.getDiscountNumber());
						if (temp >= 0) {
							item.setNewPrice(temp);
						}
					}
					if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
						Double temp = item.getPrice()
								- Math.ceil(item.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
						item.setNewPrice(temp);
					}
				}
			}
		}
		return new Response<List<ClientProductDTO>>(list, 200, "All products");
	}

}
