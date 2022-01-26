package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "study_member")
@Table
@IdClass(StudyMemberId.class)
@Getter
@Setter
public class StudyMember{
	@Id
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userno")
	Integer userno;
	
	@Id
	@ManyToOne(targetEntity = Study.class)
	@JoinColumn(name = "studyno")
	Integer studyno;
	
	@Column
	Integer position;
	@Column(name = "study_time")
	String studyTime;
	@Column(name = "is_join")
	Integer isJoin;
}
