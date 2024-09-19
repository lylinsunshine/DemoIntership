package com.shopping.site.service;

import com.shopping.site.dto.*;
import com.shopping.site.entity.Product;
import com.shopping.site.entity.Promotion;
import com.shopping.site.repository.*;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IndexService {

	private final ProductRepository productRepository;

	private final UserRepository userRepository;

	private final CategoryRepository categoryRepository;

	private final OrderRepository orderRepository;

	private final PromotionRepository promotionRepository;

	private final ModelMapper modelMapper;
	

	public Response<StatBoxDTO> getStat() {
		int product = productRepository.getTotalProduct();
		int order = orderRepository.getTotalOrderInMonth();
		int category = categoryRepository.getTotalCategory();
		int user = userRepository.getTotalUserRegistration();

		StatBoxDTO stat = new StatBoxDTO(order, user, product, category);
		return new Response<>(stat, 200, "Get Success");
	}


	public Response<ChartInfoDTO> getChartInfo() {
		List<OrderPerMonthChartDTO> opm = orderRepository.getOrderPerMonth();
		List<OrderTypePerMonthChartDTO> otpm = orderRepository.getOrderType();
		List<ProfitPerMonthChartDTO> ppm = orderRepository.getProfitPerMonth();
		List<TotalProductPerCategorChartDTO> tppc = categoryRepository.getTotalProduct();
		ChartInfoDTO c = new ChartInfoDTO(opm, otpm, tppc, ppm);
		return new Response<>(c, 200, "Get Success");
	}


	public Response<Promotion> getLastestPromotionInfo() {
		Promotion p = promotionRepository.getLastestPromotion();
		return new Response<Promotion>(p, 200, "Get Success");
	}


	public Response<List<ClientProductDTO>> getLastestProduct() {
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
		return new Response<List<ClientProductDTO>>(list, 200, "Get Success");
	}


	public Response<List<ClientProductDTO>> getHotProduct() {
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
		return new Response<List<ClientProductDTO>>(list, 200, "Get Success");
	}


	public Response<List<ClientProductDTO>> getRandomProduct() {
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
		return new Response<List<ClientProductDTO>>(list, 200, "Get Success");
	}


	public Response<Promotion> getCurrentPromotion() {
		Promotion p = promotionRepository.getCurrentPromotion();
		return new Response<>(p, 200, "Get Success");
	}

}
