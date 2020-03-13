package com.shopping.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopping.dao.IManufacturerDAO;
import com.shopping.entity.Manufacturer;
import com.shopping.service.IManufacturerService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
public class ManufacturerServiceImpl implements IManufacturerService {

	@Autowired
	private IManufacturerDAO manufacturerDAO; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseModel<PageModel<Manufacturer>> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Page<Manufacturer> page = manufacturerDAO.page(pageNumber, pageSize);		
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
	
//	@Override
//	public List<Manufacturer> findAll() {
//		// TODO Auto-generated method stub
//		return manufacturerDAO.findAll();
//	}
//
//	@Override
//	public Optional<Manufacturer> findById(int manufacturerId) {
//		// TODO Auto-generated method stub
//		return manufacturerDAO.findById(manufacturerId);
//	}
//
//	@Override
//	public void add(Manufacturer manufacturer) {
//		// TODO Auto-generated method stub
//		manufacturerDAO.insertOrUpdate(manufacturer);
//	}
//
//	@Override
//	public void deleteById(int manufacturerId) {
//		// TODO Auto-generated method stub
//		manufacturerDAO.deleteById(manufacturerId);
//	}
//
//	@Override
//	public void update(Manufacturer manufacturer) {
//		// TODO Auto-generated method stub
//		manufacturerDAO.insertOrUpdate(manufacturer);
//	}
	
	

}
