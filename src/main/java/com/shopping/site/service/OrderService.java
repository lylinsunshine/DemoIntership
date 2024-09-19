package com.shopping.site.service;

import com.shopping.site.dto.ClientOrderProductInfoDTO;
import com.shopping.site.entity.Order;
import com.shopping.site.entity.OrderDetail;
import com.shopping.site.entity.Product;
import com.shopping.site.repository.OrderDetailRepository;
import com.shopping.site.repository.OrderRepository;
import com.shopping.site.repository.ProductRepository;
import com.shopping.site.specification.OrderSpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderService {

	private OrderRepository orderRepository;

	private OrderDetailRepository orderDetailRepository;

	private ProductRepository productRepository;

	public Response<Integer> addOrder(Order order) {
		Date date = new Date();
		order.setDate(date);
		Order o = orderRepository.save(order);
		return new Response<Integer>(o.getId(), 200, "Insert Success");
	}

	public Response<Boolean> addOrderDetail(OrderDetail[] orderDetails) {
		for (OrderDetail orderDetail : orderDetails) {
			if(orderDetail.getAttribute().equals("")) {
				orderDetail.setAttribute(null);
			}
			orderDetailRepository.save(orderDetail);
		}
		return new Response<Boolean>(true, 200, "Insert Success");
	}

	public Response<Boolean> updateOrderPaymentStatusSuccess(int id) {
		Order order = orderRepository.findById(id).get();
		if(order.getPaymentMethod().equals("MOMO") && order.getPaymentStatus().equals("PENDING")) {
			order.setPaymentStatus("SUCCESS");
			return new Response<Boolean>(true, 200, "Update Success");
		} else {
			return new Response<Boolean>(false, 200, "Update Success");
		}
		
	}

	public Response<Boolean> updateOrderPaymentStatusFail(int id) {
		Order order = orderRepository.findById(id).get();
		if(order.getPaymentMethod().equals("MOMO") && order.getPaymentStatus().equals("PENDING")) {
			order.setPaymentStatus("FAIL");
			order.setDeliveryStatus("CANCELLED");
			return new Response<Boolean>(true, 200, "Update Success");
		} else {
			return new Response<Boolean>(false, 200, "Update Success");
		}
		
	}

	public Response<List<Order>> getAllOrdersByUsername(String username) {
		List<Order> list = orderRepository.findAllByUsername(username);
		for (Order order : list) {
			order.setTotalPrice(orderDetailRepository.getTotalPriceByOrderId(order.getId()));
		}
		return new Response<>(list, 200, "Get Success");
	}

	public Response<List<OrderDetail>> getOrderInformation(int orderId) {
		List<OrderDetail> list = orderDetailRepository.findAllByOrderId(orderId);
		ModelMapper modelMapper = new ModelMapper();
		for (OrderDetail orderDetail : list) {
			Product p = productRepository.findById(orderDetail.getProductEntity().getId()).get();
			ClientOrderProductInfoDTO productInfo = modelMapper.map(p, ClientOrderProductInfoDTO.class);
			orderDetail.setClientProductInfo(productInfo);
		}
		return new Response<List<OrderDetail>>(list, 200, "Get Success");
	}

	public Response<PageResponse<Order>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		String address = (String) map.get("address");
		String paymentMethod = (String) map.get("paymentMethod");
		String deliveryStatus = (String) map.get("deliveryStatus");
		String paymentStatus = (String) map.get("paymentStatus");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
		Specification<Order> spec = OrderSpec.search(address, paymentMethod, deliveryStatus, paymentStatus);

		Page<Order> page = orderRepository.findAll(spec, pageable);
		List<Order> list = page.getContent();
		for (Order order : list) {
			order.setTotalPrice(orderDetailRepository.getTotalPriceByOrderId(order.getId()));
		}
		PageResponse<Order> pageModel = new PageResponse<Order>(list, pageNumber, page.getTotalPages());
		
		return new Response<PageResponse<Order>>(pageModel, 200, "All orders");
	}

	public Response<Order> confirmPayment(int orderId) {
		Order order = orderRepository.findById(orderId).get();
		order.setDeliveryStatus("COMPLETED");
		order.setPaymentStatus("SUCCESS");
		return new Response<Order>(order, 200, "Update Success");
	}

	public Response<Order> cancelOrder(int orderId) {
		Order order = orderRepository.findById(orderId).get();
		order.setDeliveryStatus("CANCELLED");
		if(order.getPaymentMethod().equals("COD")) {
			order.setPaymentStatus("FAIL");
		}
		return new Response<Order>(order, 200, "Update Success");
	}

}
