package com.shopping.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
		
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;	
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "role")
	private String role;
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Order> orderSet;
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<ReturnRequest> returnRequestSet;
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Wishlist> wishlistSet;
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<Review> reviewSet;
	
}
