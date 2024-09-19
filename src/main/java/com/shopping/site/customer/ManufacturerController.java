package com.shopping.site.customer;

import com.shopping.site.entity.Manufacturer;
import com.shopping.site.service.ManufacturerService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {
	private final ManufacturerService manufacturerService;

	@GetMapping
	public Response<PageResponse<Manufacturer>> getManufacturerPage(@RequestParam int page, @RequestParam int size,
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
	public Response<Manufacturer> getOneManufacturer(@PathVariable int manufacturerId) {
		return manufacturerService.findById(manufacturerId);
	}

//	
	@PostMapping
	public Response<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer) {
		return manufacturerService.add(manufacturer);
	}
	
	@DeleteMapping("/{manufacturerId}")
	public Response<Manufacturer> deleteManufacturerById(@PathVariable int manufacturerId) {
		return manufacturerService.deleteById(manufacturerId);
	}
	
	@PutMapping
	public Response<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer) {
		return manufacturerService.update(manufacturer);
	}

}
