package com.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class WishlistId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "username")
	private String username;
}
