package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Manufacturer;

@Repository("manufacturerRepository")
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
	
	List<Manufacturer> findAll();
	
}
