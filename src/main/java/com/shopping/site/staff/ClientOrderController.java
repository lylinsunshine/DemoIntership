package com.shopping.site.staff;

import com.shopping.site.dto.LoginRequestDTO;
import com.shopping.site.entity.Order;
import com.shopping.site.entity.OrderDetail;
import com.shopping.site.service.OrderService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clients/order")
@RequiredArgsConstructor
public class ClientOrderController {

	private final OrderService orderService;
	
	@PostMapping("/get-order-info")
	public Response<List<Order>> getOrderInfo(@RequestBody LoginRequestDTO info) {
		return orderService.getAllOrdersByUsername(info.getUsername());
	}
	
	@GetMapping("/order-detail/{orderDetailId}")
	public Response<List<OrderDetail>> getOrderInformation(@PathVariable int orderDetailId) {
		return orderService.getOrderInformation(orderDetailId);
	}
}
