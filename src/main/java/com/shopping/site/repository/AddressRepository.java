package com.shopping.site.repository;

import java.util.List;

import com.shopping.site.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Query("FROM Address a where a.userEntity.username = ?1")
	List<Address> findByUsername(String username);
}
