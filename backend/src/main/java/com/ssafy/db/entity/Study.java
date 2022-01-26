package com.ssafy.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "study")
@Table
@Getter
@Setter
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer studyno;
	@Column
	String category;
	@Column
	String description;
	@Column
	Integer memberno;
	@Column
	String maker;
	@Column
	String studyname;
	@Column
	String image;
	@Column
	String interests;
	@Column
	String timestamp;
	
	@OneToMany(mappedBy = "studyno")
	List<StudyMember> studyMemberList = new ArrayList<>();
}

