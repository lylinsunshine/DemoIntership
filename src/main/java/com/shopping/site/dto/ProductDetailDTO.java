package com.shopping.site.dto;

import com.shopping.site.entity.Attribute;
import com.shopping.site.entity.ProductAttribute;
import com.shopping.site.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
	private int id;
	private String name;
	private Double price;
	private Double rating;
	private String image;
	private String sku;
	private String shortDescription;
	private String fullDescription;
	
	private String url;
	private String metaTitle;
	private String metaKeyword;
	private String metaDescription;
	
	private Set<ProductImage> productImageSet;
	private Set<ProductAttribute> productAttributeSet;
	private List<Attribute> attributeList;
}
