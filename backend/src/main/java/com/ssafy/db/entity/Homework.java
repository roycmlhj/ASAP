package com.ssafy.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	@Column(name = "start_date")
	String startDate;
	@Column(name = "end_date")
	String endDate;
	@Column(name = "is_active")
	Integer isActive;
	@Column
	String content;
	@Column
	String title;
	@Column
	Integer userno;
	
	@OneToMany
	@JoinColumn(name = "homeworkno")
	List<UserHomework> userHomeworkList = new ArrayList<>();
}
