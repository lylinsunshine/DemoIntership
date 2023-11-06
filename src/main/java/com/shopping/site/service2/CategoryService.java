package com.shopping.site.service2;

import com.shopping.dao.ICategoryDAO;
import com.shopping.dao.IManufacturerDAO;
import com.shopping.dao.IProductDAO;
import com.shopping.dto.ClientCategoryDTO;
import com.shopping.dto.ClientCategoryPageDTO;
import com.shopping.dto.ClientManufacturerDTO;
import com.shopping.entity.Category;
import com.shopping.entity.Manufacturer;
import com.shopping.entity.Product;
import com.shopping.service.ICategoryService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CategoryService implements ICategoryService{

	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private IManufacturerDAO manufacturerDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<String> getAllCategoriesName() {
		return categoryDAO.getAllCategoriesName();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategories();
	}

	@Override
	public List<Category> recusiveCategory() {
		return categoryDAO.recusiveCategory();
	}

	@Override
	public List<Category> findAll() {
		return null;
	}

	@Override
	public ResponseModel<PageModel<Category>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		Page<Category> page = categoryDAO.page(pageNumber, pageSize, map);
		PageModel<Category> pageModel = new PageModel<Category>(page.getContent(), pageNumber, page.getTotalPages());
		return new ResponseModel<PageModel<Category>>(pageModel, HttpStatus.OK, "All categories");
	}

	@Override
	public List<Category> getAllCategoriesNotHaveParent() {
		return categoryDAO.getAllCategoriesNotHaveParent();
	}

	@Override
	public Category addCategory(Category category) {
		categoryDAO.insertOrUpdate(category);
		return category;
	}

	@Override
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
	public ResponseModel<Boolean> isNameExist(String name) {
		boolean isNameExist = categoryDAO.isNameExist(name);
		return new ResponseModel<Boolean>(isNameExist, HttpStatus.OK, "isNameExist");
	}

	@Override
	public ResponseModel<Boolean> isUrlExist(String url) {
		boolean isUrlExist = categoryDAO.isUrlExist(url);
		return new ResponseModel<Boolean>(isUrlExist, HttpStatus.OK, "isUrlExist");
	}

	@Override
	public ResponseModel<Integer> isCategoryHaveChild(int id) {
		int count = categoryDAO.isCategoryHaveChild(id);
		return new ResponseModel<Integer>(count, HttpStatus.OK, "isCategoryHaveChild");
	}

	@Override
	public ResponseModel<ClientCategoryPageDTO> clientAllManufacturerBelongCategory(String url) {	
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
			return new ResponseModel<ClientCategoryPageDTO>(c, HttpStatus.OK, "isCategoryHaveChild");
		} else {
			return new ResponseModel<ClientCategoryPageDTO>(null, HttpStatus.OK, "isCategoryHaveChild");
		}
		
	}


}
