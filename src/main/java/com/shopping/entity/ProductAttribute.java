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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_attribute")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_attribute_id")
	private int id;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productEntity;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    private Attribute attributeEntity;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "status")
	private Boolean status;
	
	@Transient
	@Getter(value = AccessLevel.NONE)
	private int attributeId;

	public int getAttributeId() {
		return attributeEntity.getId();
	}
	
	
	
}
