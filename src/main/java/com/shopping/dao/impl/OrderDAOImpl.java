package com.shopping.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IOrderDAO;
import com.shopping.entity.Order;
import com.shopping.repository.OrderRepository;

@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements IOrderDAO {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void insertOrUpdate(Order entity) {
		// TODO Auto-generated method stub
		orderRepository.save(entity);
	}

	@Override
	public void delete(Order entity) {
		// TODO Auto-generated method stub
		orderRepository.delete(entity);
	}

	@Override
	public Page<Order> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).get();
	}

}
