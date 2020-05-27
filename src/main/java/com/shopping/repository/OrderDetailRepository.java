package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.OrderDetail;

@Repository("orderDetailRepository")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	
	@Query("SELECT SUM(o.subPrice) FROM OrderDetail o where o.orderEntity.id = ?1")
	Double getTotalPriceByOrderId(int orderId);
	
	@Query("FROM OrderDetail o where o.orderEntity.id = ?1")
	List<OrderDetail> findAllByOrderId(int orderId);
}
