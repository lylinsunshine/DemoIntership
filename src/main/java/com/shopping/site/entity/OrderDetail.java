package com.shopping.site.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping.site.dto.ClientOrderProductInfoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private int id;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order orderEntity;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product productEntity;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "sub_price")
	private Double subPrice;
	
	@Column(name = "attribute")
	private String attribute;
	
	@Transient
	private ClientOrderProductInfoDTO clientProductInfo;
	
}
