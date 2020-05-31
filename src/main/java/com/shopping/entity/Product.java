package com.shopping.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "sku", unique = true)
	private String sku;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "short_description")
	private String shortDescription;
	
	@Column(name = "full_description")
	private String fullDescription;
	
	@Column(name = "url", unique = true)
	private String url;
	
	@Column(name = "meta_title")
	private String metaTitle;
	
	@Column(name = "meta_keyword")
	private String metaKeyword;
	
	@Column(name = "meta_description")
	private String metaDescription;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category categoryEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturerEntity;
	
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.EAGER)
	private Set<ProductImage> productImageSet;
	
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.EAGER)
	private Set<ProductAttribute> productAttributeSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetailSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
	private Set<Review> reviewSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
	private Set<Wishlist> wishlistSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
	private Set<PromotionProduct> promotionProductSet;
}
