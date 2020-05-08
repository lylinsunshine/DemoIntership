package com.shopping.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IManufacturerDAO;
import com.shopping.entity.Manufacturer;
import com.shopping.service.IManufacturerService;
import com.shopping.util.Constants;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class ManufacturerServiceImpl implements IManufacturerService {

	@Autowired
	private IManufacturerDAO manufacturerDAO; 
		
	@Override
	public ResponseModel<PageModel<Manufacturer>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Manufacturer> page = manufacturerDAO.page(pageNumber, pageSize, map);		
		PageModel<Manufacturer> pageModel = new PageModel<Manufacturer>(page.getContent(), pageNumber, page.getTotalPages());
				
		return new ResponseModel<PageModel<Manufacturer>>(pageModel, HttpStatus.OK, "All manufacturers");
	}
	
//	@Override
//	public ResponseModel<List<Manufacturer>> findAll() {
//		// TODO Auto-generated method stub
//		List<Manufacturer> manufacturers = manufacturerDAO.findAll();
//		ListDTO<Manufacturer> manufacturersDTO = new ListDTO<Manufacturer>(manufacturers);
//		return new ResponseModel<List<Manufacturer>>(manufacturers, HttpStatus.OK, "All manufacturers");
//	}
	
	@Override
	public List<Manufacturer> findAll() {
		// TODO Auto-generated method stub
		return manufacturerDAO.findAll();
	}
//
	@Override
	public ResponseModel<Manufacturer> findById(int manufacturerId) {
		// TODO Auto-generated method stub
		Optional<Manufacturer> manufacturer = manufacturerDAO.findById(manufacturerId);
		if(manufacturer.isPresent())
			return new ResponseModel<Manufacturer>(manufacturer.get(), HttpStatus.OK, "Get OK");
		else 
			return new ResponseModel<Manufacturer>(null, HttpStatus.NOT_FOUND, "Get Fail");
	}

	@Override
	public ResponseModel<Manufacturer> add(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		try {
			manufacturerDAO.insertOrUpdate(manufacturer);
			return new ResponseModel<Manufacturer>(null, HttpStatus.OK, Constants.INSERT_MANUFACTURER_SUCCESSFUL);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseModel<Manufacturer>(null, HttpStatus.BAD_REQUEST, Constants.INSERT_MANUFACTURER_SUCCESSFUL);
		}
		
	}

	@Override
	public ResponseModel<Manufacturer> deleteById(int manufacturerId) {
		// TODO Auto-generated method stub
		try {
			manufacturerDAO.deleteById(manufacturerId);
			return new ResponseModel<Manufacturer>(null, HttpStatus.OK, "Delete Manufacturer Successful");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseModel<Manufacturer>(null, HttpStatus.BAD_REQUEST, "Delete Manufacturer Fail");
		} finally {
			return new ResponseModel<Manufacturer>(null, HttpStatus.BAD_REQUEST, "Delete Manufacturer Fail");
		}
		
	}

	@Override
	public ResponseModel<Manufacturer> update(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		manufacturerDAO.insertOrUpdate(manufacturer);
		return new ResponseModel<Manufacturer>(null, HttpStatus.OK, Constants.UPDATE_MANUFACTURER_SUCCESSFUL);
	}
	
	

}
