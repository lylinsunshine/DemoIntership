package com.shopping.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartInfoDTO {
	private List<OrderPerMonthChartDTO> orderPerMonthList;
	private List<OrderTypePerMonthChartDTO> orderTypeList;
	private List<TotalProductPerCategorChartDTO> totalProductList;
	private List<ProfitPerMonthChartDTO> profitList;

}
