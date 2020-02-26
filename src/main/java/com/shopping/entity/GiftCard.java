package com.shopping.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gift_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gift_card_id")
	private int id;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "receiver_email")
	private String receiverEmail;
	
	@Column(name = "sender_email")
	private String senderEmail;
	
	@Column(name = "sender_name")
	private String senderName;
}
