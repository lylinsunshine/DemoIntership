package com.shopping.site.customer;

import com.shopping.site.dto.ChartInfoDTO;
import com.shopping.site.dto.ClientProductDTO;
import com.shopping.site.dto.StatBoxDTO;
import com.shopping.site.entity.Promotion;
import com.shopping.site.service.IndexService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/index")
@RequiredArgsConstructor
public class IndexPageController {

	private final IndexService indexService;
	
	@GetMapping("/stat")
	public Response<StatBoxDTO> getStat() {
		return indexService.getStat();
	}
	
	@GetMapping("/chart")
	public Response<ChartInfoDTO> getChartInfo() {
		return indexService.getChartInfo();
	}
	
	@GetMapping("/promotion")
	public Response<Promotion> getLastestPromotion() {
		return indexService.getLastestPromotionInfo();
	}
	
	@GetMapping("/current-promotion")
	public Response<Promotion> getCurrentPromotion() {
		return indexService.getCurrentPromotion();
	}
	
	@GetMapping("/lastest-product")
	public Response<List<ClientProductDTO>> getLastestProduct() {
		return indexService.getLastestProduct();
	}
	
	@GetMapping("/hot-product")
	public Response<List<ClientProductDTO>> getHotProduct() {
		return indexService.getHotProduct();
	}
	
	@GetMapping("/random-product")
	public Response<List<ClientProductDTO>> getRandomProduct() {
		return indexService.getRandomProduct();
	}

}
