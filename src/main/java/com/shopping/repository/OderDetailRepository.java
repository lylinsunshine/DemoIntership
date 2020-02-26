package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.OrderDetail;
import com.shopping.entity.OrderDetailId;

@Repository("orderDetailRepository")
public interface OderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
