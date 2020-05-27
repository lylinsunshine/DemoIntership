package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>{
	
	@Query("FROM Order o where o.userEntity.username = ?1 order by o.id desc")
	List<Order> findAllByUsername(String username);
}
