package com.shopping.site.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientProductDTO {
	private int id;
	private String name;
	private Double price;
	private Double newPrice;
	private Double rating;
	private String image;
	private String url;
}
