package com.shopping.site.service;

import com.shopping.site.dto.ClientCategoryDTO;
import com.shopping.site.dto.ClientCategoryPageDTO;
import com.shopping.site.dto.ClientManufacturerDTO;
import com.shopping.site.entity.Category;
import com.shopping.site.entity.Manufacturer;
import com.shopping.site.entity.Product;
import com.shopping.site.repository.CategoryRepository;
import com.shopping.site.repository.ManufacturerRepository;
import com.shopping.site.repository.ProductRepository;
import com.shopping.site.specification.CategorySpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService{

	private final CategoryRepository categoryRepository;

	private final ProductRepository productRepository;

	private final ManufacturerRepository manufacturerRepository;

	private final ModelMapper modelMapper;

	public List<String> getCategoriesName() {
		return categoryRepository.getCategoriesName();
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public List<Category> getChildCategory() {
		return categoryRepository.getChildCategories();
	}

	public Response<PageResponse<Category>> page(int pageNumber, int pageSize, String name) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Category> spec = CategorySpec.search(name);
		Page<Category> page = categoryRepository.findAll(spec, pageable);
		PageResponse<Category> response = new PageResponse<Category>(page.getContent(), pageNumber, page.getTotalPages());

		return new Response<PageResponse<Category>>(response, 200, "All categories");
	}

	public List<Category> getCategoriesNotHaveParent() {
		return categoryRepository.getAllCategoriesNotHaveParent();
	}

	public Response<Category> addCategory(Category category) {
		return new Response<>(categoryRepository.save(category), 0, "");
	}

	public Category updateCategory(Category category) {
		categoryRepository.save(category);
		return category;
	}

	public Response<Boolean> isNameExist(String name) {
		Boolean isNameExist = categoryRepository.existsByName(name);
		return new Response<>(isNameExist, 200, "isNameExist");
	}

	public Response<Boolean> isUrlExist(String url) {
		Boolean isUrlExist = categoryRepository.existsByUrl(url);
		return new Response<>(isUrlExist, 200, "isUrlExist");
	}

	public Response<Integer> isCategoryHaveChild(int id) {
		int count = categoryRepository.isCategoryHaveChild(id);
		return new Response<Integer>(count, 20, "isCategoryHaveChild");
	}

	public Response<ClientCategoryPageDTO> clientAllManufacturerBelongCategory(String url) {
		Category category = categoryRepository.findByUrl(url);
		if(category!=null) {
			int categoryId = category.getId();
			List<Product> productList = productRepository.findAllByParentCategory(categoryId);
			List<Manufacturer> manuList = manufacturerRepository.findAll();
			
			List<Category> cateList = categoryRepository.findAllSubCategory(categoryId);
			
			List<ClientCategoryDTO> cateDtoList = Arrays.asList(modelMapper.map(cateList, ClientCategoryDTO[].class));
			List<ClientManufacturerDTO> manuDtoList = Arrays.asList(modelMapper.map(manuList, ClientManufacturerDTO[].class));
//			productList.forEach(product -> manuList.removeIf(manu-> manu.getId().));
//			List<ClientManufacturerDTO> list = Arrays.asList(modelMapper.map(manuList, ClientManufacturerDTO[].class));
			for (ClientManufacturerDTO item : manuDtoList) {
				int temp = 0;
				for (Product product : productList) {
					if(item.getId()==product.getManufacturerEntity().getId()) {
						temp++;
					}
				}
				item.setTotalProduct(temp);
			}
			List<ClientManufacturerDTO> l1 = new ArrayList<>(manuDtoList);
			l1.removeIf(client -> client.getTotalProduct()==0);
			
			for (ClientCategoryDTO item : cateDtoList) {
				int temp = 0;
				for (Product product : productList) {
					if(item.getId()==product.getCategoryEntity().getId()) {
						temp++;
					}
				}
				item.setTotalProduct(temp);
			}
			
			List<ClientCategoryDTO> l2 = new ArrayList<>(cateDtoList);
			l2.removeIf(client -> client.getTotalProduct()==0);
			ClientCategoryPageDTO c = new ClientCategoryPageDTO(l1, l2);
			return new Response<>(c, 200, "isCategoryHaveChild");
		} else {
			return new Response<>(null, 200, "isCategoryHaveChild");
		}

	}
}
