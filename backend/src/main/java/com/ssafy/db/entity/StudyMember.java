package com.ssafy.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "study_member")
@Getter
@Setter
@IdClass(StudyMemberId.class)
public class StudyMember implements Serializable{
	@Id
	int userno;
	@Id
	int studyno;
	@Column
	int position;
	@Column(name = "study_time")
	String studyTime;
	@Column(name = "is_join")
	int isJoin;

//	@ManyToOne
//	@JoinColumn(name = "study_rewfwefqfefq")
//	Study study;
}
