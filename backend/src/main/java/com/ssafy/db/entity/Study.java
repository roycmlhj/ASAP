package com.ssafy.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.catalina.startup.HomesUserDatabase;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@DynamicInsert
@DynamicUpdate
@Entity(name = "study")
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
	
	
	@OneToMany
	@JoinColumn(name = "studyno")
	List<StudyMember> studyMemberList = new ArrayList<StudyMember>();
	
	@OneToMany
	@JoinColumn(name = "studyno")
	List<Homework> homeworkList = new ArrayList<Homework>();
	
	@OneToMany
	@JoinColumn(name = "studyno")
	List<Schedule> scheduleList = new ArrayList<Schedule>();
	
	@OneToMany
	@JoinColumn(name = "studyno")
	List<Board> board = new ArrayList<Board>(); 
}
