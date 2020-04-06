package com.shopping.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Manufacturer;

@Repository("manufacturerRepository")
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>, JpaSpecificationExecutor<Manufacturer> {
	
	//List<Manufacturer> findAll();
}
