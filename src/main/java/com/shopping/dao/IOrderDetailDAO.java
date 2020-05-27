package com.shopping.dao;

import java.util.List;

import com.shopping.entity.OrderDetail;

public interface IOrderDetailDAO extends GenericDAO<OrderDetail, Integer>{
	
	Double getTotalPriceByOrderId(int orderId);
	
	List<OrderDetail> findAllByOrderId(int orderId);
}
