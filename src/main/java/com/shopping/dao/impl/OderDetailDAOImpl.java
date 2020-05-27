package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IOrderDetailDAO;
import com.shopping.entity.OrderDetail;
import com.shopping.repository.OrderDetailRepository;

@Repository("orderDetailDAO")
@Transactional
public class OderDetailDAOImpl implements IOrderDetailDAO{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public void insertOrUpdate(OrderDetail entity) {
		// TODO Auto-generated method stub
		orderDetailRepository.save(entity);
	}

	@Override
	public void delete(OrderDetail entity) {
		// TODO Auto-generated method stub
		orderDetailRepository.delete(entity);
	}

	@Override
	public Page<OrderDetail> page(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return orderDetailRepository.getTotalPriceByOrderId(orderId);
	}

	@Override
	public List<OrderDetail> findAllByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findAllByOrderId(orderId);
	}

}
