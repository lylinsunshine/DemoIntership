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
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "url")
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_id")
    private Category parent;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Set<Category> children;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY)
	private Set<Product> productSet;
}
