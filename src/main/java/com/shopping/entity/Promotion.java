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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_id")
	private int id;
	
	@Column(name = "promotion_name")
	private String name;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	@Getter(value = AccessLevel.NONE)
	private Date startDate;
	
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	@Getter(value = AccessLevel.NONE)
	private Date endDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	public Date getStartDate() {
		return startDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	public Date getEndDate() {
		return endDate;
	}

	@Column(name = "discount_percent")
	private String discountPercent;
	
	@Column(name = "discount_number")
	private String discountNumber;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "type_id")
	private int typeId;
	
	@Column(name = "image")
	private String image;
}
