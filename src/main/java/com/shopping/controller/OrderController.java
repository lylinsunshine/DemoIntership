package com.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Order;
import com.shopping.service.IOrderService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public ResponseModel<PageModel<Order>> getOrderPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String address, @RequestParam(required = false) String paymentMethod,
			@RequestParam(required = false) String deliveryStatus, @RequestParam(required = false) String paymentStatus) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address", address);
		map.put("paymentMethod", paymentMethod);
		map.put("deliveryStatus", deliveryStatus);
		map.put("paymentStatus", paymentStatus);

		return orderService.findAll(page, size, map);
	}
	
	@GetMapping("/confirm/{orderId}")
	public ResponseModel<Order> confirmPayment(@PathVariable int orderId) {
		return orderService.confirmPayment(orderId);
	}
	
	@GetMapping("/cancel/{orderId}")
	public ResponseModel<Order> cancelOrder(@PathVariable int orderId) {
		return orderService.cancelOrder(orderId);
	}
}
