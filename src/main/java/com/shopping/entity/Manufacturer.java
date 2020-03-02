package com.shopping.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manufacturer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturer_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "image")
	private String image;
	
	@JsonIgnore
	@OneToMany(mappedBy = "manufacturerEntity", fetch = FetchType.LAZY)
	private Set<Product> productSet;
}
