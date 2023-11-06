package com.shopping.service;

import java.util.List;

import com.shopping.dto.ChartInfoDTO;
import com.shopping.dto.ClientProductDTO;
import com.shopping.dto.StatBoxDTO;
import com.shopping.entity.Promotion;
import com.shopping.util.ResponseModel;

public interface IIndexService {
	ResponseModel<StatBoxDTO> getStat();
	
	ResponseModel<ChartInfoDTO> getChartInfo();
	
	ResponseModel<Promotion> getLastestPromotionInfo();
	
	ResponseModel<List<ClientProductDTO>> getLastestProduct();
	
	ResponseModel<List<ClientProductDTO>> getHotProduct();
	
	ResponseModel<List<ClientProductDTO>> getRandomProduct();
	
	ResponseModel<Promotion> getCurrentPromotion();
}
