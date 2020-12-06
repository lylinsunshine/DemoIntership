package com.shopping.clientcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.LoginRequestDTO;
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
	
	@PostMapping("/get-order-info")
	public ResponseModel<List<Order>> getOrderInfo(@RequestBody LoginRequestDTO info) {
		return orderService.getAllOrdersByUsername(info.getUsername());
	}
	
	@GetMapping("/order-detail/{orderDetailId}")
	public ResponseModel<List<OrderDetail>> getOrderInformation(@PathVariable int orderDetailId) {
		return orderService.getOrderInformation(orderDetailId);
	}
}
