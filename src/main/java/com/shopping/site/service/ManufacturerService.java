package com.shopping.site.service;

import com.shopping.site.entity.Manufacturer;
import com.shopping.site.repository.ManufacturerRepository;
import com.shopping.site.specification.ManufacturerSpec;
import com.shopping.site.util.Constants;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManufacturerService{

	private final ManufacturerRepository manufacturerRepository;

	public Response<PageResponse<Manufacturer>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		String name = (String) map.get("name");
		String address = (String) map.get("address");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
		Specification<Manufacturer> spec = ManufacturerSpec.hasNameAndAdress(name, address);
		Page<Manufacturer> page = manufacturerRepository.findAll(spec, pageable);

		PageResponse<Manufacturer> pageModel = new PageResponse<>(page.getContent(), pageNumber, page.getTotalPages());
				
		return new Response<>(pageModel, 200, "All manufacturers");
	}

	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}

	public Response<Manufacturer> findById(int manufacturerId) {
		Optional<Manufacturer> manufacturer = manufacturerRepository.findById(manufacturerId);
		if(manufacturer.isPresent())
			return new Response<>(manufacturer.get(), 200, "Get OK");
		else 
			return new Response<>(null, 404, "Get Fail");
	}

	public Response<Manufacturer> add(Manufacturer manufacturer) {
		try {
			manufacturerRepository.save(manufacturer);
			return new Response<>(null, 200, Constants.INSERT_MANUFACTURER_SUCCESSFUL);
		} catch (Exception e) {
			return new Response<>(null, 200, Constants.INSERT_MANUFACTURER_SUCCESSFUL);
		}
		
	}

	public Response<Manufacturer> deleteById(int manufacturerId) {
		try {
			manufacturerRepository.deleteById(manufacturerId);
			return new Response<>(null, 200, "Delete Manufacturer Successful");
		} catch (Exception e) {
			return new Response<>(null, 200, "Delete Manufacturer Fail");
		}	
	}

	public Response<Manufacturer> update(Manufacturer manufacturer) {
		manufacturerRepository.save(manufacturer);
		return new Response<>(null, 200, Constants.UPDATE_MANUFACTURER_SUCCESSFUL);
	}
	
	

}
