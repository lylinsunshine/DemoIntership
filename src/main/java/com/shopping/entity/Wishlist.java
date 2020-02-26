package com.shopping.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
	
	@EmbeddedId
	private WishlistId wishlistId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private Product productEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("username")
	@JoinColumn(name = "username")
	private User userEntity;
}
