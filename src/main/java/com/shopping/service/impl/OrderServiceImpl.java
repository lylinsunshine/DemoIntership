package com.shopping.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IOrderDAO;
import com.shopping.dao.IOrderDetailDAO;
import com.shopping.dao.IProductDAO;
import com.shopping.dto.ClientOrderProductInfoDTO;
import com.shopping.entity.Order;
import com.shopping.entity.OrderDetail;
import com.shopping.entity.Product;
import com.shopping.service.IOrderService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private IOrderDAO orderDAO;
	
	@Autowired
	private IOrderDetailDAO orderDetailDAO;
	
	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseModel<Integer> addOrder(Order order) {
		// TODO Auto-generated method stub
		Date date = new Date();
		order.setDate(date);
		Order o = orderDAO.addOrder(order);	
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
			order.setDeliveryStatus("CANCELLED");
			return new ResponseModel<Boolean>(true, HttpStatus.OK, "Update Success");
		} else {
			return new ResponseModel<Boolean>(false, HttpStatus.OK, "Update Success");
		}
		
	}

	@Override
	public ResponseModel<List<Order>> getAllOrdersByUsername(String username) {
		// TODO Auto-generated method stub
		List<Order> list = orderDAO.findAllByUsername(username);
		for (Order order : list) {
			order.setTotalPrice(orderDetailDAO.getTotalPriceByOrderId(order.getId()));
		}
		return new ResponseModel<List<Order>>(list, HttpStatus.OK, "Get Success");
	}

	@Override
	public ResponseModel<List<OrderDetail>> getOrderInformation(int orderId) {
		// TODO Auto-generated method stub
		List<OrderDetail> list = orderDetailDAO.findAllByOrderId(orderId);
		for (OrderDetail orderDetail : list) {
			Product p = productDAO.findById(orderDetail.getProductEntity().getId()).get();			
			ClientOrderProductInfoDTO productInfo = modelMapper.map(p, ClientOrderProductInfoDTO.class);
			orderDetail.setClientProductInfo(productInfo);
		}
		return new ResponseModel<List<OrderDetail>>(list, HttpStatus.OK, "Get Success");
	}

	@Override
	public ResponseModel<PageModel<Order>> findAll(int pageNumber, int pageSize, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Order> page = orderDAO.page(pageNumber, pageSize, map);
		List<Order> list = page.getContent();
		for (Order order : list) {
			order.setTotalPrice(orderDetailDAO.getTotalPriceByOrderId(order.getId()));
		}
		PageModel<Order> pageModel = new PageModel<Order>(list, pageNumber, page.getTotalPages());
		
		return new ResponseModel<PageModel<Order>>(pageModel, HttpStatus.OK, "All orders");
	}

	@Override
	public ResponseModel<Order> confirmPayment(int orderId) {
		// TODO Auto-generated method stub
		Order order = orderDAO.findById(orderId);
		order.setDeliveryStatus("COMPLETED");
		order.setPaymentStatus("SUCCESS");
		return new ResponseModel<Order>(order, HttpStatus.OK, "Update Success");
	}

}
