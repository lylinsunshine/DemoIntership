package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

	@EmbeddedId
	private OrderDetailId orderDetailId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("order_id")
	@JoinColumn(name = "order_id")
	private Order orderEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private Product productEntity;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private int quantity;
	
	
	
}
