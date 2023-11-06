package com.shopping.site.service2;

import com.shopping.dao.IManufacturerDAO;
import com.shopping.entity.Manufacturer;
import com.shopping.service.IManufacturerService;
import com.shopping.util.Constants;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ManufacturerService implements IManufacturerService {

	@Autowired
	private IManufacturerDAO manufacturerDAO; 
		
	@Override
	public ResponseModel<PageModel<Manufacturer>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		Page<Manufacturer> page = manufacturerDAO.page(pageNumber, pageSize, map);		
		PageModel<Manufacturer> pageModel = new PageModel<Manufacturer>(page.getContent(), pageNumber, page.getTotalPages());
				
		return new ResponseModel<PageModel<Manufacturer>>(pageModel, HttpStatus.OK, "All manufacturers");
	}
	
//	@Override
//	public ResponseModel<List<Manufacturer>> findAll() {
//		List<Manufacturer> manufacturers = manufacturerDAO.findAll();
//		ListDTO<Manufacturer> manufacturersDTO = new ListDTO<Manufacturer>(manufacturers);
//		return new ResponseModel<List<Manufacturer>>(manufacturers, HttpStatus.OK, "All manufacturers");
//	}
	
	@Override
	public List<Manufacturer> findAll() {
		return manufacturerDAO.findAll();
	}

	@Override
	public ResponseModel<Manufacturer> findById(int manufacturerId) {
		Optional<Manufacturer> manufacturer = manufacturerDAO.findById(manufacturerId);
		if(manufacturer.isPresent())
			return new ResponseModel<Manufacturer>(manufacturer.get(), HttpStatus.OK, "Get OK");
		else 
			return new ResponseModel<Manufacturer>(null, HttpStatus.NOT_FOUND, "Get Fail");
	}

	@Override
	public ResponseModel<Manufacturer> add(Manufacturer manufacturer) {
		try {
			manufacturerDAO.insertOrUpdate(manufacturer);
			return new ResponseModel<Manufacturer>(null, HttpStatus.OK, Constants.INSERT_MANUFACTURER_SUCCESSFUL);
		} catch (Exception e) {
			return new ResponseModel<Manufacturer>(null, HttpStatus.BAD_REQUEST, Constants.INSERT_MANUFACTURER_SUCCESSFUL);
		}
		
	}

	@Override
	public ResponseModel<Manufacturer> deleteById(int manufacturerId) {
		try {
			manufacturerDAO.deleteById(manufacturerId);
			return new ResponseModel<Manufacturer>(null, HttpStatus.OK, "Delete Manufacturer Successful");
		} catch (Exception e) {
			return new ResponseModel<Manufacturer>(null, HttpStatus.BAD_REQUEST, "Delete Manufacturer Fail");
		}	
	}

	@Override
	public ResponseModel<Manufacturer> update(Manufacturer manufacturer) {
		manufacturerDAO.insertOrUpdate(manufacturer);
		return new ResponseModel<Manufacturer>(null, HttpStatus.OK, Constants.UPDATE_MANUFACTURER_SUCCESSFUL);
	}
	
	

}
