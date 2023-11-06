package com.shopping.site.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
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
	@Getter(value = AccessLevel.NONE)
	private Date date;
	
	@Transient	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Double totalPrice;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	public Date getDate() {
		return date;
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User userEntity;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetailSet;
	
	@JsonIgnore
	@OneToOne(mappedBy = "orderEntity")
	private ReturnRequest returnRequestEntiy;
}
