package com.shopping.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "return_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "return_request_id")
	private int id;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToOne
	@MapsId
	private Order orderEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User userEntity;
}
