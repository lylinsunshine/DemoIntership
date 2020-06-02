package com.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface ProfitPerMonthChartDTO {
	int getMonth();
	Double getTotal();
}
