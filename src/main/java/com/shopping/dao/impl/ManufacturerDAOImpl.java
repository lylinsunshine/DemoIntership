package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IManufacturerDAO;
import com.shopping.entity.Manufacturer;
import com.shopping.repository.ManufacturerRepository;
import com.shopping.specification.ManufacturerSpec;

@Repository("manufacturerDAO")
@Transactional
public class ManufacturerDAOImpl implements IManufacturerDAO {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public void insertOrUpdate(Manufacturer entity) {
		// TODO Auto-generated method stub
		manufacturerRepository.save(entity);
	}

	@Override
	public void delete(Manufacturer entity) {
		// TODO Auto-generated method stub
		manufacturerRepository.delete(entity);
	}

	@Override
	public void deleteById(int manufacturerId) {
		// TODO Auto-generated method stub
		manufacturerRepository.deleteById(manufacturerId);
	}

	@Override
	public List<Manufacturer> findAll() {
		// TODO Auto-generated method stub
		return manufacturerRepository.findAll();
	}

	@Override
	public Optional<Manufacturer> findById(int manufacturerId) {
		// TODO Auto-generated method stub
		return manufacturerRepository.findById(manufacturerId);
	}

	@Override
	public Page<Manufacturer> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String name = (String) map.get("name");
		String address = (String) map.get("address");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Manufacturer> spec = ManufacturerSpec.hasNameAndAdress(name, address);
		return manufacturerRepository.findAll(spec, pageable);
	}

}
