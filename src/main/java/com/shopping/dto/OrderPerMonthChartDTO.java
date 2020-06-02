package com.shopping.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface OrderPerMonthChartDTO {
	 int getMonth();
	int getTotal();
}
