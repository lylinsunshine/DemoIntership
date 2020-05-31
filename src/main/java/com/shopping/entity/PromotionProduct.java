package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "promotion_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_product_id")
	private int id;
	
	@Transient
	private int promotionId;
	
	@Transient
	private int productId;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_id")
	private Promotion promotionEntity;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product productEntity;
	

	
}
