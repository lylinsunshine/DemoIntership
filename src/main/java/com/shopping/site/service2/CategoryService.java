package com.shopping.site.service2;

import com.shopping.dto.ClientCategoryDTO;
import com.shopping.dto.ClientCategoryPageDTO;
import com.shopping.dto.ClientManufacturerDTO;
import com.shopping.entity.Category;
import com.shopping.entity.Manufacturer;
import com.shopping.entity.Product;
import com.shopping.site.entity.Category;
import com.shopping.site.entity.Manufacturer;
import com.shopping.site.entity.Product;
import com.shopping.site.repository.CategoryRepository;
import com.shopping.site.repository.ManufacturerRepository;
import com.shopping.site.repository.ProductRepository;
import com.shopping.site.specification.CategorySpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService{

	private final CategoryRepository categoryRepository;

	private final ProductRepository productRepository;

	private final ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<String> getCategoriesName() {
		return categoryRepository.getCategoriesName();
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public List<Category> getChildCategory() {
		return categoryRepository.getChildCategories();
	}

	public PageResponse<Category> page(int pageNumber, int pageSize, String name) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Category> spec = CategorySpec.search(name);
		Page<Category> page = categoryRepository.findAll(spec, pageable);

		return new PageResponse<>(
				page.getContent(),
				page.getTotalPages(),
				page.getTotalElements(),
				page.getPageable().getPageNumber(),
				page.getSize(),
				0,
				"");
	}

	public List<Category> getCategoriesNotHaveParent() {
		return categoryDAO.getAllCategoriesNotHaveParent();
	}

	public Response<Category> addCategory(Category category) {
		return new Response<>(categoryRepository.save(category), 0, "");
	}

	public Category updateCategory(Category category) {
//		int id = category.getId();
//		Category c = categoryDAO.findById(id);
//		c.setName(category.getName());
//		c.setDescription(category.getDescription());
//		c.getParent().setId(category.getParent().getId());
		categoryDAO.insertOrUpdate(category);
		return null;
	}

	@Override
	public Response<Boolean> isNameExist(String name) {
		boolean isNameExist = categoryDAO.isNameExist(name);
		return new Response<Boolean>(isNameExist, HttpStatus.OK, "isNameExist");
	}

	@Override
	public Response<Boolean> isUrlExist(String url) {
		boolean isUrlExist = categoryDAO.isUrlExist(url);
		return new Response<Boolean>(isUrlExist, HttpStatus.OK, "isUrlExist");
	}

	@Override
	public Response<Integer> isCategoryHaveChild(int id) {
		int count = categoryDAO.isCategoryHaveChild(id);
		return new Response<Integer>(count, HttpStatus.OK, "isCategoryHaveChild");
	}

	@Override
	public Response<ClientCategoryPageDTO> clientAllManufacturerBelongCategory(String url) {
		Category category = categoryDAO.findByUrl(url);
		if(category!=null) {
			int categoryId = category.getId();
			List<Product> productList = productDAO.findAllByParentCategory(categoryId);
			List<Manufacturer> manuList = manufacturerDAO.findAll();
			
			List<Category> cateList = categoryDAO.findAllSubCategory(categoryId);
			
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
			List<ClientManufacturerDTO> l1 = new ArrayList<ClientManufacturerDTO>(manuDtoList);
			l1.removeIf(client -> client.getTotalProduct()==0);
			
			for (ClientCategoryDTO item : cateDtoList) {
				int temp = 0;
				for (Product product : productList) {
					if(item.getId()==product.getCategoryEntity().getId()) {
						temp++;
					}
				}
				item.setTotalProduct(temp);
			};
			
			List<ClientCategoryDTO> l2 = new ArrayList<ClientCategoryDTO>(cateDtoList);
			l2.removeIf(client -> client.getTotalProduct()==0);
			ClientCategoryPageDTO c = new ClientCategoryPageDTO(l1, l2);
			return new Response<ClientCategoryPageDTO>(c, HttpStatus.OK, "isCategoryHaveChild");
		} else {
			return new Response<ClientCategoryPageDTO>(null, HttpStatus.OK, "isCategoryHaveChild");
		}

	}
}
