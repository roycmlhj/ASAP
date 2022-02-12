package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@DynamicInsert
@DynamicUpdate
@Entity(name = "emailconfirm")
@Getter
@Setter
public class EmailConfirm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column
	String code;
	@Column(unique = true)
	String email;
	@Column
	String timestamp;
}
