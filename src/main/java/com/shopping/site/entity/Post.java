package com.shopping.site.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "overview")
	private String overview;

	@Column(name = "body", columnDefinition = "TEXT")
	private String body;
	
	@Column(name = "image")
	private String image;
	
	
	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@Getter(value = AccessLevel.NONE)
	private Date dateCreated;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	
	
}
