package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_homework")
@Table
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class UserHomework {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userhomeworkno;
	@Column
	Integer userno;
	@Column
	Integer homeworkno;
	@Column(name = "is_done")
	Integer isDone;
	@Column
	String filename;
	@Column
	String filedata;
}
