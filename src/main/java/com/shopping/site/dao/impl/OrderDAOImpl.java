package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IOrderDAO;
import com.shopping.entity.Order;
import com.shopping.repository.OrderRepository;
import com.shopping.specification.OrderSpec;

@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements IOrderDAO {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void insertOrUpdate(Order entity) {
		orderRepository.save(entity);
	}

	@Override
	public void delete(Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public Page<Order> page(int pageNumber, int pageSize, Map<String, Object> map) {
		String address = (String) map.get("address");
		String paymentMethod = (String) map.get("paymentMethod");
		String deliveryStatus = (String) map.get("deliveryStatus");
		String paymentStatus = (String) map.get("paymentStatus");
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
		Specification<Order> spec = OrderSpec.search(address, paymentMethod, deliveryStatus, paymentStatus);
		return orderRepository.findAll(spec, pageable);
	}

	@Override
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order findById(int id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order> findAllByUsername(String username) {
		return orderRepository.findAllByUsername(username);
	}

}
