package com.shopping.site.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.ChartInfoDTO;
import com.shopping.dto.ClientProductDTO;
import com.shopping.dto.StatBoxDTO;
import com.shopping.entity.Promotion;
import com.shopping.service.IIndexService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexPageController {
	
	@Autowired
	private IIndexService indexService;
	
	@GetMapping("/stat")
	public ResponseModel<StatBoxDTO> getStat() {
		return indexService.getStat();
	}
	
	@GetMapping("/chart")
	public ResponseModel<ChartInfoDTO> getChartInfo() {
		return indexService.getChartInfo();
	}
	
	@GetMapping("/promotion")
	public ResponseModel<Promotion> getLastestPromotion() {
		return indexService.getLastestPromotionInfo();
	}
	
	@GetMapping("/current-promotion")
	public ResponseModel<Promotion> getCurrentPromotion() {
		return indexService.getCurrentPromotion();
	}
	
	@GetMapping("/lastest-product")
	public ResponseModel<List<ClientProductDTO>> getLastestProduct() {
		return indexService.getLastestProduct();
	}
	
	@GetMapping("/hot-product")
	public ResponseModel<List<ClientProductDTO>> getHotProduct() {
		return indexService.getHotProduct();
	}
	
	@GetMapping("/random-product")
	public ResponseModel<List<ClientProductDTO>> getRandomProduct() {
		return indexService.getRandomProduct();
	}

}
