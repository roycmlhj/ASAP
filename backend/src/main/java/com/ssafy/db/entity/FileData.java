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
@Entity(name = "file")
@Getter
@Setter
public class FileData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer fileno;
	@Column
	Integer boardno;
	@Column
	String filepath;
	@Column
	String filename;
	@Column
	String ogfilename;
}
