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

@Entity
@Table(name = "study_member")
@IdClass(StudyMemberId.class)
@Getter
@Setter
public class StudyMember{
	@Id
	@ManyToOne
	@JoinColumn(name = "userno")
	int userno;
	@Id
	@ManyToOne
	@JoinColumn(name = "studyno")
	int studyno;
	@Column
	int position;
	@Column(name = "study_time")
	String studyTime;
	@Column(name = "is_join")
	int isJoin;
}
