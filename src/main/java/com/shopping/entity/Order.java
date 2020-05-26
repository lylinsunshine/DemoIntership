package com.shopping.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;
		
	@Column(name = "address")
	private String address;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "delivery_status")
	private String deliveryStatus;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User userEntity;
	
	@OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetailSet;
	
	@OneToOne(mappedBy = "orderEntity")
	private ReturnRequest returnRequestEntiy;
}
