package com.shopping.site.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCategoryDTO {
	private int id;
	private String name;
	private int totalProduct;
}
