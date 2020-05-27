package com.shopping.service;

import java.util.List;
import java.util.Map;

import com.shopping.entity.Order;
import com.shopping.entity.OrderDetail;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IOrderService {
	
	ResponseModel<Integer> addOrder(Order order);
	
	ResponseModel<Boolean> addOrderDetail(OrderDetail[] orderDetails);
	
	ResponseModel<Boolean> updateOrderPaymentStatusSuccess(int id);
	
	ResponseModel<Boolean> updateOrderPaymentStatusFail(int id);
	
	ResponseModel<List<Order>> getAllOrdersByUsername(String username);
	
	ResponseModel<List<OrderDetail>> getOrderInformation(int orderId);
	
	ResponseModel<PageModel<Order>> findAll(int pageNumber, int pageSize, Map<String, Object> map);
	
	ResponseModel<Order> confirmPayment(int orderId);
}
