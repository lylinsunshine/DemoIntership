package com.shopping.service.impl;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IOrderDAO;
import com.shopping.dao.IOrderDetailDAO;
import com.shopping.entity.Order;
import com.shopping.entity.OrderDetail;
import com.shopping.repository.OrderRepository;
import com.shopping.service.IOrderService;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private IOrderDAO orderDAO;
	
	@Autowired
	private IOrderDetailDAO orderDetailDAO;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ResponseModel<Integer> addOrder(Order order) {
		// TODO Auto-generated method stub
		//System.out.println(order.getDate());
		Order o = orderDAO.addOrder(order);
		
//		Order o2 = orderDAO.findById(8);
//		//System.out.println(o2.getDate());
//		List<Order> list = orderRepository.findAll();
//		for (Order order2 : list) {
//			System.out.println(order2.getDate());
//		}
		return new ResponseModel<Integer>(o.getId(), HttpStatus.OK, "Insert Success");
	}

	@Override
	public ResponseModel<Boolean> addOrderDetail(OrderDetail[] orderDetails) {
		for (OrderDetail orderDetail : orderDetails) {
			if(orderDetail.getAttribute().equals("")) {
				orderDetail.setAttribute(null);
			}
			orderDetailDAO.insertOrUpdate(orderDetail);
		}
		// TODO Auto-generated method stub
		return new ResponseModel<Boolean>(true, HttpStatus.OK, "Insert Success");
	}

	@Override
	public ResponseModel<Boolean> updateOrderPaymentStatusSuccess(int id) {
		// TODO Auto-generated method stub
		Order order = orderDAO.findById(id);
		if(order.getPaymentMethod().equals("MOMO") && order.getPaymentStatus().equals("PENDING")) {
			order.setPaymentStatus("SUCCESS");
			return new ResponseModel<Boolean>(true, HttpStatus.OK, "Update Success");
		} else {
			return new ResponseModel<Boolean>(false, HttpStatus.OK, "Update Success");
		}
		
	}
	
	@Override
	public ResponseModel<Boolean> updateOrderPaymentStatusFail(int id) {
		// TODO Auto-generated method stub
		Order order = orderDAO.findById(id);
		if(order.getPaymentMethod().equals("MOMO") && order.getPaymentStatus().equals("PENDING")) {
			order.setPaymentStatus("FAIL");
			return new ResponseModel<Boolean>(true, HttpStatus.OK, "Update Success");
		} else {
			return new ResponseModel<Boolean>(false, HttpStatus.OK, "Update Success");
		}
		
	}

}
