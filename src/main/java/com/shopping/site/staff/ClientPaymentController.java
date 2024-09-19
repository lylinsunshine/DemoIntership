package com.shopping.site.staff;

import com.shopping.site.entity.Order;
import com.shopping.site.entity.OrderDetail;
import com.shopping.site.momo.MoMoPayment;
import com.shopping.site.momo.MoMoResponse;
import com.shopping.site.service.OrderService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/clients/payment")
@RequiredArgsConstructor
public class ClientPaymentController {

	private final OrderService orderService;
	
	@GetMapping
	public ResponseEntity<MoMoResponse> getProductInfo() {
		MoMoPayment p = new MoMoPayment();
		return p.payment();
	}
	
	@PostMapping
	public Response<Integer> addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
	@PostMapping("/order-detail")
	public Response<Boolean> addOrderDetail(@RequestBody OrderDetail[] orderDetails) {
		return orderService.addOrderDetail(orderDetails);
	}
	
	@PostMapping("/update-order-success/{id}")
	public Response<Boolean> updateOrderPaymentStatusSuccess(@PathVariable int id) {
		return orderService.updateOrderPaymentStatusSuccess(id);
	}
	
	@PostMapping("/update-order-fail/{id}")
	public Response<Boolean> updateOrderPaymentStatusFail(@PathVariable int id) {
		return orderService.updateOrderPaymentStatusFail(id);
	}
}
