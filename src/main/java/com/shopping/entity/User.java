package com.shopping.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Blindfold Gang
 *
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
		
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_number")
	private String phoneNumber;	
	
	@Column(name = "role")
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Order> orderSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<ReturnRequest> returnRequestSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Wishlist> wishlistSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Review> reviewSet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Address> addressSet;
	
}
