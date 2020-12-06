package com.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Manufacturer;
import com.shopping.service.IManufacturerService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

	@Autowired
	private IManufacturerService manufacturerService;

	@GetMapping
	public ResponseModel<PageModel<Manufacturer>> getManufacturerPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String name, @RequestParam(required = false) String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("address", address);
		return manufacturerService.findAll(page, size, map);
	}

	@GetMapping("/select")
	public List<Manufacturer> getAllManufacturers() {
		return manufacturerService.findAll();
	}

	@GetMapping("/{manufacturerId}")
	public ResponseModel<Manufacturer> getOneManufacturer(@PathVariable int manufacturerId) {
		return manufacturerService.findById(manufacturerId);
	}

//	
	@PostMapping
	public ResponseModel<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer) {
		return manufacturerService.add(manufacturer);
	}
	
	@DeleteMapping("/{manufacturerId}")
	public ResponseModel<Manufacturer> deleteManufacturerById(@PathVariable int manufacturerId) {
		return manufacturerService.deleteById(manufacturerId);
	}
	
	@PutMapping
	public ResponseModel<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer) {
		return manufacturerService.update(manufacturer);
	}

}
