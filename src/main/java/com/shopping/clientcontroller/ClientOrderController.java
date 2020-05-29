package com.shopping.clientcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Order;
import com.shopping.entity.OrderDetail;
import com.shopping.service.IOrderService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/order")
public class ClientOrderController {

	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/{username}")
	public ResponseModel<List<Order>> getOrderInfo(@PathVariable String username) {
		return orderService.getAllOrdersByUsername(username);
	}
	
	@GetMapping("/order-detail/{orderDetailId}")
	public ResponseModel<List<OrderDetail>> getOrderInformation(@PathVariable int orderDetailId) {
		return orderService.getOrderInformation(orderDetailId);
	}
}
