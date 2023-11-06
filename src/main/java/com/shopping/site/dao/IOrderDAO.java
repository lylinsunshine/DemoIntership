package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Order;

public interface IOrderDAO extends GenericDAO<Order, Integer>{
	
	Order addOrder(Order order);
	
	Order findById(int id);
	
	List<Order> findAllByUsername(String username);
}
