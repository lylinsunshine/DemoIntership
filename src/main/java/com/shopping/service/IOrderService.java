package com.shopping.service;

import com.shopping.entity.Order;
import com.shopping.entity.OrderDetail;
import com.shopping.util.ResponseModel;

public interface IOrderService {
	
	ResponseModel<Integer> addOrder(Order order);
	
	ResponseModel<Boolean> addOrderDetail(OrderDetail[] orderDetails);
	
	ResponseModel<Boolean> updateOrderPaymentStatusSuccess(int id);
	
	ResponseModel<Boolean> updateOrderPaymentStatusFail(int id);
}
