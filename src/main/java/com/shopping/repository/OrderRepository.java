package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
