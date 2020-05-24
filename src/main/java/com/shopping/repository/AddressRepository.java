package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Query("FROM Address a where a.userEntity.username = ?1")
	List<Address> findByUsername(String username);
}
