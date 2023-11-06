package com.shopping.site.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Order;
import com.shopping.entity.OrderDetail;
import com.shopping.momo.MoMoPayment;
import com.shopping.momo.MoMoResponse;
import com.shopping.service.IOrderService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/payment")
public class ClientPaymentController {
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public ResponseEntity<MoMoResponse> getProductInfo() {
		MoMoPayment p = new MoMoPayment();
		System.out.println(p.payment());
		return p.payment();
	}
	
	@PostMapping
	public ResponseModel<Integer> addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
	@PostMapping("/order-detail")
	public ResponseModel<Boolean> addOrderDetail(@RequestBody OrderDetail[] orderDetails) {
		return orderService.addOrderDetail(orderDetails);
	}
	
	@PostMapping("/update-order-success/{id}")
	public ResponseModel<Boolean> updateOrderPaymentStatusSuccess(@PathVariable int id) {
		return orderService.updateOrderPaymentStatusSuccess(id);
	}
	
	@PostMapping("/update-order-fail/{id}")
	public ResponseModel<Boolean> updateOrderPaymentStatusFail(@PathVariable int id) {
		return orderService.updateOrderPaymentStatusFail(id);
	}
}
