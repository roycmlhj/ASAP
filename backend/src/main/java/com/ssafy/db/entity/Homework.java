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
@Entity(name = "homework")
@Getter
@Setter
public class Homework {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer homeworkno;
	@Column
	Integer studyno;
	@Column
	String start_date;
	@Column
	String end_date;
	@Column(name = "is_active")
	Integer isActive;
	@Column
	String content;
}
