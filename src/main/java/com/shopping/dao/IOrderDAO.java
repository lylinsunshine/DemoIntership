package com.shopping.dao;

import com.shopping.entity.Order;

public interface IOrderDAO extends GenericDAO<Order, Integer>{
	
	Order addOrder(Order order);
	
	Order findById(int id);
}
