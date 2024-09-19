package com.shopping.site.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientPromotionIndexDTO {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String discountPercent;
	private String discountNumber;
	private String type;
	private int typeId;
	private String typeName;
	private String image;
}
