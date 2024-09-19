package com.shopping.site.customer;

import com.shopping.site.entity.Order;
import com.shopping.site.service.OrderService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@GetMapping
	public Response<PageResponse<Order>> getOrderPage(@RequestParam int page, @RequestParam int size,
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
	public Response<Order> confirmPayment(@PathVariable int orderId) {
		return orderService.confirmPayment(orderId);
	}
	
	@GetMapping("/cancel/{orderId}")
	public Response<Order> cancelOrder(@PathVariable int orderId) {
		return orderService.cancelOrder(orderId);
	}
}
