package com.shopping.site.service2;

import com.shopping.dto.*;
import com.shopping.entity.Product;
import com.shopping.entity.Promotion;
import com.shopping.repository.*;
import com.shopping.service.IIndexService;
import com.shopping.util.ResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class IndexService implements IIndexService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public ResponseModel<StatBoxDTO> getStat() {
		int product = productRepository.getTotalProduct();
		int order = orderRepository.getTotalOrderInMonth();
		int category = categoryRepository.getTotalCategory();
		int user = userRepository.getTotalUserRegistration();
		StatBoxDTO stat = new StatBoxDTO(order, user, product, category);
		return new ResponseModel<StatBoxDTO>(stat, HttpStatus.OK, "Get Success");
	}


	@Override
	public ResponseModel<ChartInfoDTO> getChartInfo() {
		List<OrderPerMonthChartDTO> opm = orderRepository.getOrderPerMonth();
		List<OrderTypePerMonthChartDTO> otpm = orderRepository.getOrderType();
		List<ProfitPerMonthChartDTO> ppm = orderRepository.getProfitPerMonth();
		List<TotalProductPerCategorChartDTO> tppc = categoryRepository.getTotalProduct();
		ChartInfoDTO c = new ChartInfoDTO(opm, otpm, tppc, ppm);
		return new ResponseModel<ChartInfoDTO>(c, HttpStatus.OK, "Get Success");
	}


	@Override
	public ResponseModel<Promotion> getLastestPromotionInfo() {
		Promotion p = promotionRepository.getLastestPromotion();
		return new ResponseModel<Promotion>(p, HttpStatus.OK, "Get Success");
	}


	@Override
	public ResponseModel<List<ClientProductDTO>> getLastestProduct() {
		List<Product> pList = productRepository.getLastestProduct();
		List<ClientProductDTO> list = Arrays.asList(modelMapper.map(pList, ClientProductDTO[].class));
		
		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product product : l2) {
			for (ClientProductDTO item : list) {
				if (item.getId() == product.getId()) {
					if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
						Double temp = item.getPrice() - Double.parseDouble(p.getDiscountNumber());
						if (temp >= 0) {
							item.setNewPrice(temp);
						}
					}
					if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
						Double temp = item.getPrice()
								- Math.ceil(item.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
						item.setNewPrice(temp);
					}
				}
			}
		}
		return new ResponseModel<List<ClientProductDTO>>(list, HttpStatus.OK, "Get Success");
	}


	@Override
	public ResponseModel<List<ClientProductDTO>> getHotProduct() {
		List<Product> pList = productRepository.getTopProduct();
		List<ClientProductDTO> list = Arrays.asList(modelMapper.map(pList, ClientProductDTO[].class));
		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product product : l2) {
			for (ClientProductDTO item : list) {
				if (item.getId() == product.getId()) {
					if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
						Double temp = item.getPrice() - Double.parseDouble(p.getDiscountNumber());
						if (temp >= 0) {
							item.setNewPrice(temp);
						}
					}
					if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
						Double temp = item.getPrice()
								- Math.ceil(item.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
						item.setNewPrice(temp);
					}
				}
			}
		}
		return new ResponseModel<List<ClientProductDTO>>(list, HttpStatus.OK, "Get Success");
	}


	@Override
	public ResponseModel<List<ClientProductDTO>> getRandomProduct() {
		Promotion p = promotionRepository.getCurrentPromotion();
		List<Product> pList = productRepository.getRandomProduct(p.getId());
		List<ClientProductDTO> list = Arrays.asList(modelMapper.map(pList, ClientProductDTO[].class));
		List<Product> l2 = productRepository.findByPromotionId(p.getId());
		for (Product product : l2) {
			for (ClientProductDTO item : list) {
				if (item.getId() == product.getId()) {
					if (p.getDiscountNumber() != null && !p.getDiscountNumber().equals("")) {
						Double temp = item.getPrice() - Double.parseDouble(p.getDiscountNumber());
						if (temp >= 0) {
							item.setNewPrice(temp);
						}
					}
					if (p.getDiscountPercent() != null && !p.getDiscountPercent().equals("")) {
						Double temp = item.getPrice()
								- Math.ceil(item.getPrice() * Integer.parseInt(p.getDiscountPercent()) / 100);
						item.setNewPrice(temp);
					}
				}
			}
		}
		return new ResponseModel<List<ClientProductDTO>>(list, HttpStatus.OK, "Get Success");
	}


	@Override
	public ResponseModel<Promotion> getCurrentPromotion() {
		Promotion p = promotionRepository.getCurrentPromotion();
		return new ResponseModel<Promotion>(p, HttpStatus.OK, "Get Success");
	}

}
