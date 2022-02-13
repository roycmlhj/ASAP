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
@Entity(name = "study_board")
@Getter
@Setter
public class StudyBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer boardno;
	@Column
	Integer type;
	@Column
	Integer studyno;
	@Column
	Integer userno;
	@Column
	String title;
	@Column
	String content;
	@Column
	String timestamp;
	
	@OneToMany
	@JoinColumn(name = "boardno")
	List<FileData> fileList = new ArrayList<FileData>();
}
