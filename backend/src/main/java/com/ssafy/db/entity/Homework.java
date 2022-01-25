package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "homework")
@Getter
@Setter
public class Homework {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int homeworkno;
	@Column
	int studyno;
	@Column
	String start_date;
	@Column
	String end_date;
	@Column(name = "is_active")
	int isActive;
	@Column
	String content;
}
